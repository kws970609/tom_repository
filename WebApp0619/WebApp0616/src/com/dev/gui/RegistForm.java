package com.dev.gui;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.dev.model.board.NoticeDAO;
import com.dev.model.notice.Notice;

public class RegistForm extends JFrame{
	JTextField t_title;
	JTextField t_writer;
	JTextArea area;
	JButton bt;
	NoticeDAO noticeDAO;
	
	
	public RegistForm() {
		t_title= new JTextField(20);
		t_writer= new JTextField(20);
		area = new JTextArea();
		bt = new JButton("게시물 등록");
		noticeDAO = new NoticeDAO();
		
		//스타일적용
		t_title.setPreferredSize(new Dimension(200,30));
		t_writer.setPreferredSize(new Dimension(200,30));
		area.setPreferredSize(new Dimension(200,220));
		
		setLayout(new FlowLayout());
		add(t_title);
		add(t_writer);
		add(area);
		add(bt);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setSize(250,400);
		setLocationRelativeTo(null);
		
		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				regist();
			}
		});
	}
	
	public void regist() {
		//CRUD를 담당하는 DAO공용객체 를 사용하자!!
		Notice notice = new Notice();
		notice.setTitle(t_title.getText());//제목
		notice.setWriter(t_writer.getText());//작성자
		notice.setContent(area.getText());//내용
		
		int result = noticeDAO.insert(notice);
		if(result!=0) {
			JOptionPane.showMessageDialog(this, "등록 성공");
		}else {
			JOptionPane.showMessageDialog(this, "등록 실패");
		}
	}
	public static void main(String[] args) {
		new RegistForm();
	}
}






































