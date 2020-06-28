package com.dev.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import com.dev.model.board.NoticeDAO;
import com.dev.model.notice.Notice;

/*
 * DAO를 이용하여 게시물 가져오기!!
 * */
public class ListTable extends JFrame{
	JTable table;
	JScrollPane scroll;
	JButton bt;
	
	//컬럼 제목
	String[] column = {"notice_id","title","writer","regdate","hit"};
	String[][] data = new String[0][0];//채워질 데이터
	NoticeDAO noticeDAO= new NoticeDAO();
	
	public ListTable() {
		table = new JTable(new AbstractTableModel() {
			
			public Object getValueAt(int row, int col) {
				return data[row][col];
			}
			
			public int getRowCount() {
				return data.length;
			}
			
			public int getColumnCount() {
				return column.length;
			}
			
			public String getColumnName(int col) {
				return column[col];
			}
		});
		scroll = new  JScrollPane(table);
		bt = new JButton("Load");
		
		//스타일 적용
		table.setPreferredSize(new Dimension(500,450));
		
		//조립
		setLayout(new FlowLayout());
		add(scroll);
		add(bt);
		
		getList();
		
		table.setPreferredSize(new Dimension(500,450));
		
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				int col = table.getSelectedColumn();
				String notice_id =(String)table.getValueAt(row, 0);
				System.out.println(data);
				
				//게시물 한건이다!!
				Notice notice = noticeDAO.select(Integer.parseInt(notice_id));
				StringBuilder sb = new StringBuilder();
				sb.append("notice_id : "+notice.getNotice_id()+"\n");
				sb.append("title : "+notice.getTitle()+"\n");
				sb.append("writer : "+notice.getWriter()+"\n");
				sb.append("content : "+notice.getContent()+"\n");
				sb.append("regdate : "+notice.getRegdate()+"\n");
				sb.append("hit : "+notice.getHit()+"\n");
				
				JOptionPane.showMessageDialog(ListTable.this, sb.toString());
			}
		});
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500,500);
		setVisible(true);
		setLocationRelativeTo(null);
	}
	
	//DAO 를 이용하여 게시물 목록 가져오기!!
	public void getList() {
		List<Notice> list = noticeDAO.selectAll();
		//JTable은 2차원 배열 형태로 데이터를 취급하므로, list를 2차원 스트링 배열로 옮겨 심자!!
		data = new String[list.size()][column.length];
		
		for(int i=0; i<list.size();i++) {
			Notice notice = list.get(i);
			data[i][0]=Integer.toString(notice.getNotice_id());
			data[i][1]=notice.getTitle();
			data[i][2]=notice.getWriter();
			data[i][3]=notice.getRegdate();
			data[i][4]=Integer.toString(notice.getHit());
		}
		table.updateUI();
	}
	
	public static void main(String[] args) {
		new ListTable();
	}
}




































