package data.repositories;

import data.models.Book;
import data.repositories.contracts.IBookRepository;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.*;

/**
 * Хранилище за управление на обекти Book (книги) в базирано на XML съхранение.
 *
 * @author Емил Долчинков
 */
public class BookXmlRepository implements IBookRepository {

    private static List<Book> items = new ArrayList<>();

    /**
     * Извлича всички книги от хранилището.
     *
     * @return Списък с всички книги.
     */
    @Override
    public List<Book> GetAll() {
        return Collections.unmodifiableList(items);
    }

    /**
     * Извлича книга по нейното заглавие.
     *
     * @param title Заглавието на книгата, която трябва да бъде намерена.
     * @return Книгата с посоченото заглавие или null, ако не е намерена.
     */
    @Override
    public Book GetByName(String title) {
        return items.stream()
                .filter(b -> b.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);
    }

    /**
     * Извлича книга по нейния уникален идентификатор.
     *
     * @param id ID-то на книгата, която трябва да бъде намерена.
     * @return Книгата с посоченото ID или null, ако не е намерена.
     */
    @Override
    public Book GetById(int id) {
        return items.stream()
                .filter(b -> b.getId() == id)
                .findFirst()
                .orElse(null);
    }

    /**
     * Добавя нова книга в хранилището.
     *
     * @param record Книгата, която да бъде добавена.
     * @return true, ако книгата е добавена успешно, false в противен случай.
     */
    @Override
    public boolean Add(Book record) {
        if (record == null) return false;
        items.add(record);
        return true;
    }

    /**
     * Актуализира съществуваща книга в хранилището.
     *
     * @param record Книгата с актуализираната информация.
     * @return true, ако книгата е актуализирана успешно, false в противен случай.
     */
    @Override
    public boolean Update(Book record) {
        if (record == null) return false;
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId() == record.getId()) {
                items.set(i, record);
                return true;
            }
        }
        return false;
    }

    /**
     * Премахва книга от хранилището.
     *
     * @param record Книгата, която да бъде премахната.
     * @return true, ако книгата е премахната успешно, false в противен случай.
     */
    @Override
    public boolean Remove(Book record) {
        return record != null && items.remove(record);
    }

    /**
     * Премахва книга от хранилището по нейното ID.
     *
     * @param id ID-то на книгата, която да бъде премахната.
     * @return true, ако книгата е премахната успешно, false в противен случай.
     */
    @Override
    public boolean Remove(Integer id) {
        return items.removeIf(b -> b.getId() == id);
    }

    /**
     * Изчиства всички книги от хранилището.
     *
     * @return винаги true, което показва, че хранилището е изчистено.
     */
    @Override
    public boolean Clear() {
        items.clear();
        return true;
    }

    /**
     * Зарежда книги от XML файл.
     *
     * @param fileName Пътят до XML файла.
     * @return true, ако книгите са заредени успешно, false в противен случай.
     */
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

            NodeList bookNodes = doc.getElementsByTagName("book");
            for (int i = 0; i < bookNodes.getLength(); i++) {
                Element el = (Element) bookNodes.item(i);
                Book book = new Book();
                book.setId(Integer.parseInt(getText(el, "id")));
                book.setTitle(getText(el, "title"));
                book.setAuthor(getText(el, "author"));
                book.setGenre(getText(el, "genre"));
                book.setDescription(getText(el, "description"));
                book.setYear(Integer.parseInt(getText(el, "year")));
                book.setRating(Double.parseDouble(getText(el, "rating")));

                List<String> keywords = new ArrayList<>();
                NodeList kwNodes = el.getElementsByTagName("keyword");
                for (int k = 0; k < kwNodes.getLength(); k++) {
                    keywords.add(kwNodes.item(k).getTextContent().trim());
                }
                book.setKeywords(keywords);

                items.add(book);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Записва текущия списък с книги в XML файл.
     *
     * @param fileName Пътят до XML файла.
     * @return true, ако книгите са записани успешно, false в противен случай.
     */
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

            NodeList existingBooks = root.getElementsByTagName("books");
            while (existingBooks.getLength() > 0) {
                root.removeChild(existingBooks.item(0));
            }

            Element booksEl = doc.createElement("books");
            for (Book book : items) {
                Element b = doc.createElement("book");
                appendText(doc, b, "id",         String.valueOf(book.getId()));
                appendText(doc, b, "title",       book.getTitle());
                appendText(doc, b, "author",      book.getAuthor());
                appendText(doc, b, "genre",       book.getGenre());
                appendText(doc, b, "description", book.getDescription());
                appendText(doc, b, "year",        String.valueOf(book.getYear()));
                appendText(doc, b, "rating",      String.valueOf(book.getRating()));

                Element kwRoot = doc.createElement("keywords");
                if (book.getKeywords() != null) {
                    for (String kw : book.getKeywords()) {
                        appendText(doc, kwRoot, "keyword", kw);
                    }
                }
                b.appendChild(kwRoot);
                booksEl.appendChild(b);
            }
            root.appendChild(booksEl);

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

    /**
     * Извлича текстовото съдържание на посочен таг в родителски елемент.
     *
     * @param parent Родителският елемент.
     * @param tag Името на тага.
     * @return Текстовото съдържание на тага или празен стринг, ако не е намерен.
     */
    private String getText(Element parent, String tag) {
        NodeList nl = parent.getElementsByTagName(tag);
        if (nl.getLength() == 0) return "";
        return nl.item(0).getTextContent().trim();
    }

    /**
     * Добавя нов елемент с текстово съдържание към родителски елемент.
     *
     * @param doc XML документът.
     * @param parent Родителският елемент.
     * @param tag Името на тага за новия елемент.
     * @param value Текстовото съдържание за новия елемент.
     */
    private void appendText(Document doc, Element parent, String tag, String value) {
        Element el = doc.createElement(tag);
        el.appendChild(doc.createTextNode(value == null ? "" : value));
        parent.appendChild(el);
    }
}