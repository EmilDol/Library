package data.repositories;

import data.models.User;
import data.repositories.contracts.IUserRepository;

import java.util.List;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.*;

public class UserXmlRepository implements IUserRepository {

    private static List<User> items = new ArrayList<>();

    @Override
    public List<User> GetAll() {
        return Collections.unmodifiableList(items);
    }

    @Override
    public User GetByName(String username) {
        return items.stream()
                .filter(u -> u.getUsername().equalsIgnoreCase(username))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean Add(User record) {
        if (record == null) return false;
        items.add(record);
        return true;
    }

    @Override
    public boolean Update(User record) {
        if (record == null) return false;
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getUsername().equalsIgnoreCase(record.getUsername())) {
                items.set(i, record);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean Remove(User record) {
        return record != null && items.remove(record);
    }

    @Override
    public boolean Remove(Integer id) {
        if (id < 0 || id >= items.size()) return false;
        items.remove((int) id);
        return true;
    }

    @Override
    public boolean Clear() {
        items.clear();
        return true;
    }

    @Override
    public User GetById(int id) {
        return null;
    }

    @Override
    public boolean Load(String fileName) {
        try {
            File file = new File(fileName);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            if (!file.exists()) {
                Document emptyDoc = builder.newDocument();
                Element emptyRoot = emptyDoc.createElement("database");
                emptyDoc.appendChild(emptyRoot);

                Transformer transformer = TransformerFactory.newInstance().newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.transform(new DOMSource(emptyDoc), new StreamResult(file));
                return true;
            }

            Document doc = builder.parse(file);
            doc.getDocumentElement().normalize();

            items.clear();

            NodeList userNodes = doc.getElementsByTagName("user");
            for (int i = 0; i < userNodes.getLength(); i++) {
                Element el = (Element) userNodes.item(i);
                User user = new User();
                user.setUsername(getText(el, "username"));
                user.setPassword(getText(el, "password"));
                user.setAdmin(Boolean.parseBoolean(getText(el, "isAdmin")));
                items.add(user);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean SaveFile(String fileName) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document doc;
            Element root;
            File file = new File(fileName);

            if (file.exists()) {
                doc = builder.parse(file);
                doc.getDocumentElement().normalize();
                root = doc.getDocumentElement();
            } else {
                doc = builder.newDocument();
                root = doc.createElement("database");
                doc.appendChild(root);
            }

            NodeList existingUsers = root.getElementsByTagName("users");
            while (existingUsers.getLength() > 0) {
                root.removeChild(existingUsers.item(0));
            }

            Element usersEl = doc.createElement("users");
            for (User user : items) {
                Element u = doc.createElement("user");
                appendText(doc, u, "username", user.getUsername());
                appendText(doc, u, "password", user.getPassword());
                appendText(doc, u, "isAdmin",  String.valueOf(user.isAdmin()));
                usersEl.appendChild(u);
            }
            root.appendChild(usersEl);

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.transform(new DOMSource(doc), new StreamResult(file));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private String getText(Element parent, String tag) {
        NodeList nl = parent.getElementsByTagName(tag);
        if (nl.getLength() == 0) return "";
        return nl.item(0).getTextContent().trim();
    }

    private void appendText(Document doc, Element parent, String tag, String value) {
        Element el = doc.createElement(tag);
        el.appendChild(doc.createTextNode(value == null ? "" : value));
        parent.appendChild(el);
    }
}