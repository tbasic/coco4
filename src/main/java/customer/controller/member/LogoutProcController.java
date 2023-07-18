package customer.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import customer.controller.Controller;
import customer.dao.MemberDao;
import customer.vo.Member;

public class LogoutProcController implements Controller{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("LogoutProcController~~~~~");
		
		//세션제거
//		notice.do로 이동
		//request.getSession().invalidate();
		request.getSession().removeAttribute("uid");
		
		response.sendRedirect("../customer/notice.do");
			
//		request.getRequestDispatcher("loginForm.jsp").forward(request, response);
		
	}

}
