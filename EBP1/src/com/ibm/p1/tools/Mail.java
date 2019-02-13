package com.ibm.p1.tools;
import java.util.Date;  
import java.util.Properties;  
  
import javax.mail.Authenticator;  
import javax.mail.Message;  
import javax.mail.Multipart;  
import javax.mail.PasswordAuthentication;  
import javax.mail.Session;  
import javax.mail.Transport;  
import javax.mail.internet.MimeBodyPart;  
import javax.mail.internet.MimeMessage;  
import javax.mail.internet.MimeMultipart;  
import javax.mail.internet.MimeUtility;  
import javax.mail.internet.InternetAddress; 

public class Mail {
	String to = ""; // 收件人  
    String from = ""; // 发件人  
    String host = ""; // smtp主机  
    String username = ""; // 用户名  
    String password = ""; // 密码  
    String subject = ""; // 邮件主题  
    String content = ""; // 邮件正文  
  
    public Mail() {  
    }  
  
    public Mail(String to, String from, String host, String username,  
            String password, String subject, String content) {  
        this.to = to;  
        this.from = from;  
        this.host = host;  
        this.username = username;  
        this.password = password;  
        this.subject = subject;  
        this.content = content;  
    }  
  
    /** 
     * 把主题转换为中文 
     *  
     * @param strText 
     * @return 
     */  
    public String transferChinese(String strText) {  
  
        try {  
            strText = MimeUtility.encodeText(new String(strText.getBytes(),  
                    "GB2312"), "GB2312", "B");  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
  
        return strText;  
    }  
  
    /** 
     * 发送邮件 
     *  
     * @return 成功返回true，失败返回false 
     */  
    public boolean sendMail() {  
        // 构造mail session  
        Properties props = System.getProperties();  
        props.put("mail.smtp.host", host);  
        props.put("mail.smtp.auth", "true");  
        Session session = Session.getDefaultInstance(props,  
                new Authenticator() {  
                    public PasswordAuthentication getPasswordAuthentication() {  
                        return new PasswordAuthentication(username, password);  
                    }  
                });  
        try {  
            // 构造MimeMessage并设定基本的值，创建消息对象  
            MimeMessage msg = new MimeMessage(session);  
            // 设置消息内容  
            msg.setFrom(new InternetAddress(from));  
            
            System.out.println(from);  
            // 把邮件地址映射到Internet地址上  
            InternetAddress[] address = { new InternetAddress(to) };  
            System.out.println(to);
            /** 
             * setRecipient（Message.RecipientType type, Address 
             * address），用于设置邮件的接收者。<br> 
             * 有两个参数，第一个参数是接收者的类型，第二个参数是接收者。<br> 
             * 接收者类型可以是Message.RecipientType .TO，Message 
             * .RecipientType.CC和Message.RecipientType.BCC，TO表示主要接收人，CC表示抄送人 
             * ，BCC表示秘密抄送人。接收者与发送者一样，通常使用InternetAddress的对象。 
             */  
            msg.setRecipients(Message.RecipientType.TO, address);  
            // 设置邮件的标题  
            subject = transferChinese(subject);  
            msg.setSubject(subject);  
            // 构造Multipart  
            Multipart mp = new MimeMultipart();  
  
            // 向Multipart添加正文  
            MimeBodyPart mbpContent = new MimeBodyPart();  
            // 设置邮件内容(纯文本格式)  
            // mbpContent.setText(content);  
            // 设置邮件内容(HTML格式)  
            mbpContent.setContent(content, "text/html;charset=utf-8");  
            // 向MimeMessage添加（Multipart代表正文）  
            mp.addBodyPart(mbpContent);  
            // 向Multipart添加MimeMessage  
            msg.setContent(mp);  
            // 设置邮件发送的时间。  
            msg.setSentDate(new Date());  
            // 发送邮件  
            Transport.send(msg);  
        } catch (Exception e) {  
            e.printStackTrace();  
            return false;  
        }  
        return true;  
    }  
  
    public String getTo() {  
        return to;  
    }  
  
    public void setTo(String to) {  
        this.to = to;  
    }  
  
    public String getFrom() {  
        return from;  
    }  
  
    public void setFrom(String from) {  
        this.from = from;  
    }  
  
    public String getHost() {  
        return host;  
    }  
  
    public void setHost(String host) {  
        this.host = host;  
    }  
  
    public String getUsername() {  
        return username;  
    }  
  
    public void setUsername(String username) {  
        this.username = username;  
    }  
  
    public String getPassword() {  
        return password;  
    }  
  
    public void setPassword(String password) {  
        this.password = password;  
    }  
  
    public String getSubject() {  
        return subject;  
    }  
  
    public void setSubject(String subject) {  
        this.subject = subject;  
    }  
  
    public String getContent() {  
        return content;  
    }  
  
    public void setContent(String content) {  
        this.content = content;  
    }  
    /*
    public static void main(String[] args) {
    	Mail mail = new Mail();
    	StringBuffer sb = new StringBuffer(); 
		sb.append("亲爱的用户"+ "lzw"+"：您好！<br><br>");  
        sb.append("        您收到这封这封电子邮件是因为您 (也可能是某人冒充您的名义) 申请了一个新的密码。假如这不是您本人所申请, 请不用理会<br>这封电子邮件, 但是如果您持续收到这类的信件骚扰, 请您尽快联络管理员。<br><br>");  
        sb.append("        要使用新的密码, 请使用以下链接启用密码。<br><br>");  
        sb.append("        <a href='http://localhost:8080/EBP1/userAction_resetPassword.action?username="+"刘紫薇"+"'>http://localhost:8080/EBP1/userAction_resetPassword.action?username="+"刘紫薇"+"</a>");  
        sb.append("<br><br>我们将一如既往、热忱的为您服务！");  
        sb.append("<br>用户服务支持：<a href='mailto:liuzwei@cn.ibm.com'>liuzwei@cn.ibm.com</a><br><br><br>");  
        // strm[1]第一个跟第二个@间内容,strm[strm.length - 1]最后一@内容 //  
        // 创建邮件   
        mail.setTo("807376089@qq.com");  
        mail.setFrom("poseidon_eb2013@163.com");// 你的邮箱  
        mail.setHost("smtp.163.com");  
        mail.setUsername("poseidon_eb2013@163.com");// 用户  
        mail.setPassword("poseidon");// 密码  
        mail.setSubject("[Poseidon]找回您的账户密码");  
        mail.setContent(sb.toString()); 
        boolean result = mail.sendMail();
        System.out.println("result :"+result);
    }
    */
}
