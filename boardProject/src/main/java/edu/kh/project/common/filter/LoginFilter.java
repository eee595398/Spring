package edu.kh.project.common.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


// Filter : 클라이언트의 요청/응답을 걸러내거나, 첨가하는 클래스
// 클라이언트 <-> Filter <-> Dispatcher Servlet


// @WebFilter : 해당 클래스를 필터로 등록하고, 지정된 주소롤 요청이 올때마다 동작


//필터가 여러개 있을 수 있는데 이름으로 순서를 지정함               // 마이페이지로 시작하는 모든걸
@WebFilter(filterName = "loginFilter", urlPatterns = {"/myPage/*"})
public class LoginFilter implements Filter {

	public void init(FilterConfig fConfig) throws ServletException {
		
		// 초기화 메서드 
		// 서버가 켜질때 필터가 생성되고 생성시 초기화 용도로 사용하는 메서드 
		// 코드가 변경할떄 destroy가 삭제하고 다시 한번 초기화한다 
	
		System.out.println("---로그인 필터 생성 --");
		
	}
	
	public void destroy() {
		//필터 코드가 변경 되었을때 
		// 변경 이전 필터를 파괴하는 메서드
		System.out.println("---이전 로그인 필터 파괴--");
		
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 필터링 코드를 작성하는 메서드
		// http,ftp,메일등 모든 요청을 받기때문 부모타입을 사용함
		
		// 1) ServletRequest, ServletResponse 다운 캐스팅
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp =(HttpServletResponse)response;
		
		// 2) HttpServletRequest를 이용해서 HttpSession 얻어오기 
		HttpSession session = req.getSession();
		
		// 3) session 에서 "loginMember" key를 가진 속성을 얻어와 
		// null인 경우 메인페이지 redirect 시키기 
		
		if(session.getAttribute("loginMember")== null) {
			
			resp.sendRedirect("/");
		}
		
		// 4) 로그인 상태인 경우 다음 필터 또는 DispatcherServlet으로 전달
		else {
			chain.doFilter(request, response);
			
		}
		
		
		
		
	
	}



}