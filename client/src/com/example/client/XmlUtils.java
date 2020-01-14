package com.example.client;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParser;

import android.util.Xml;



public class XmlUtils {
	public List<Student> dom2xml(InputStream is) throws Exception {
        //һϵ�еĳ�ʼ��
        List<Student> list = new ArrayList<Student>();
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();//��ȡ DocumentBuilderFactory ����ʵ��
       DocumentBuilder builder = factory.newDocumentBuilder();//ʹ�õ�ǰ���õĲ�������һ���µ� DocumentBuilder ʵ����
        //���Document����
        Document document = builder.parse(is);//������ URI �����ݽ���Ϊһ�� XML �ĵ������ҷ���һ���µ� DOM Document ����(url�����·��---��Ŀ)
        //���student��List
        NodeList studentList = document.getElementsByTagName("student");
        //����student��ǩ
        for (int i = 0; i < studentList.getLength(); i++) {
            //���student��ǩ
            Node node_student = studentList.item(i);
            //���student��ǩ����ı�ǩ
            NodeList childNodes = node_student.getChildNodes();
            //�½�student����
            Student student = new Student();
            //����student��ǩ����ı�ǩ
            for (int j = 0; j < childNodes.getLength(); j++) {
                //���name��nickName��ǩ
                Node childNode = childNodes.item(j);
                //�ж���name����nickName
                if ("name".equals(childNode.getNodeName())) {
                    String name = childNode.getTextContent();
                    student.setName(name);
                    //��ȡname������
                    NamedNodeMap nnm = childNode.getAttributes();
                    //��ȡsex���ԣ�����ֻ��һ�����ԣ�����ȡ0
                    Node n = nnm.item(0);
                    student.setSex(n.getTextContent());
                } else if ("nickName".equals(childNode.getNodeName())) {
                    String nickName = childNode.getTextContent();
                    student.setNickName(nickName);
                }
            }
            //�ӵ�List��
            list.add(student);
        }
        return list;
    }
    
    public List<Student> sax2xml(InputStream is) throws Exception {
        SAXParserFactory spf = SAXParserFactory.newInstance();
        //��ʼ��Sax������
        SAXParser sp = spf.newSAXParser();
        //�½�����������
        MyHandler handler = new MyHandler();
        //����������������
        sp.parse(is, handler);
        //����List
        return handler.getList();
    }

    public class MyHandler extends DefaultHandler {

        private List<Student> list;
        private Student student;
        //���ڴ洢��ȡ����ʱ����
        private String tempString;

        /**
         * �������ĵ���ʼ���ã�һ������ʼ������
         *
         * @throws SAXException
         */
        @Override
        public void startDocument() throws SAXException {
            list = new ArrayList<Student>();
            super.startDocument();
        }

        /**
         * �������ĵ�ĩβ���ã�һ�������ղ���
         *
         * @throws SAXException
         */
        @Override
        public void endDocument() throws SAXException {
            super.endDocument();
        }

        /**
         * ÿ����һ��Ԫ�ؾ͵��ø÷���
         *
         * @param uri
         * @param localName
         * @param qName
         * @param attributes
         * @throws SAXException
         */
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if ("student".equals(qName)) {
                //����student��ǩ
                student = new Student();
            } else if ("name".equals(qName)) {
                //��ȡname���������
                String sex = attributes.getValue("sex");
                student.setSex(sex);
            }
            super.startElement(uri, localName, qName, attributes);
        }

        /**
         * ����Ԫ�صĽ�β����
         *
         * @param uri
         * @param localName
         * @param qName
         * @throws SAXException
         */
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if ("student".equals(qName)) {
                list.add(student);
            }
            if ("name".equals(qName)) {
                student.setName(tempString);
            } else if ("nickName".equals(qName)) {
                student.setNickName(tempString);
            }
          
        }

        /**
         * �����������ݵ���
         *
         * @param ch
         * @param start
         * @param length
         * @throws SAXException
         */
        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            tempString = new String(ch, start, length);
            super.characters(ch, start, length);
        }

        /**
         * ��ȡ��List
         *
         * @return
         */
        public List<Student> getList() {
            return list;
        }
    }
    public List<Student> pull2xml(InputStream is) throws Exception {
        List<Student> list = null;
        Student student = null;
        //����xmlPull������
        XmlPullParser parser = Xml.newPullParser();
        ///��ʼ��xmlPull������
        parser.setInput(is, "utf-8");
        //��ȡ�ļ�������
        int type = parser.getEventType();
        //�����ж��ļ����ͽ��ж�ȡ
        while (type != XmlPullParser.END_DOCUMENT) {
            switch (type) {
                //��ʼ��ǩ
                case XmlPullParser.START_TAG:
                    if ("students".equals(parser.getName())) {
                        list = new ArrayList<Student>();
                    } else if ("student".equals(parser.getName())) {
                        student = new Student();
                    } else if ("name".equals(parser.getName())) {
                        //��ȡsex����
                        String sex = parser.getAttributeValue(null,"sex");
                        student.setSex(sex);
                        //��ȡnameֵ
                        String name = parser.nextText();
                        student.setName(name);
                    } else if ("nickName".equals(parser.getName())) {
                        //��ȡnickNameֵ
                        String nickName = parser.nextText();
                        student.setNickName(nickName);
                    }
                    break;
                //������ǩ
                case XmlPullParser.END_TAG:
                    if ("student".equals(parser.getName())) {
                        list.add(student);
                    }
                    break;
            }
            //�������¶�ȡ��ǩ����
            type = parser.next();
        }
        return list;
    }
}
