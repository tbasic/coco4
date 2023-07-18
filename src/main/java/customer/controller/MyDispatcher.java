package customer.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import customer.controller.member.LoginController;
import customer.controller.member.LoginProcController;
import customer.controller.member.LogoutProcController;
import customer.controller.notice.DownloadController;
import customer.controller.notice.NoticeController;
import customer.controller.notice.NoticeDelProcController;
import customer.controller.notice.NoticeDetailController;
import customer.controller.notice.NoticeEditController;
import customer.controller.notice.NoticeEditProcController;
import customer.controller.notice.NoticeRegController;
import customer.controller.notice.NoticeRegProcController;

public class MyDispatcher extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("http신호호호호호호");
		String uri=request.getRequestURI();
		String conPath=request.getContextPath();
		String com=uri.substring(conPath.length());
		System.out.println(com);
		
//		NoticeDetailController controller1=new NoticeDetailController();
//		NoticeEditController controller2=new NoticeEditController();
		Controller controller=null;
		
		try {
			if(com.equals("/customer/noticeDetail.do")) {
				controller=new NoticeDetailController();
			}else if(com.equals("/customer/noticeEdit.do")) {
				controller=new NoticeEditController();
			}else if(com.equals("/customer/noticeEditProc.do")) {
				controller=new NoticeEditProcController();
			}else if(com.equals("/customer/noticeReg.do")) {
				controller=new NoticeRegController();
			}else if(com.equals("/customer/noticeRegProc.do")) {
				controller=new NoticeRegProcController();
			}else if(com.equals("/customer/noticeDelProc.do")) {
				controller=new NoticeDelProcController();
			}else if(com.equals("/customer/notice.do")) {
				controller=new NoticeController();
			}
			
			
			else if(com.equals("/login/login.do")) {
				controller=new LoginController();
			}
//			else if(com.equals("/login/loginProc.do")) {
//				controller=new LoginProcController();
//			}else if(com.equals("/login/logoutProc.do")) {
//				controller=new LogoutProcController();
//			}else if(com.equals("/customer/download.do")) {
//				controller=new DownloadController();
//			}
			controller.execute(request, response);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		
	}

}
