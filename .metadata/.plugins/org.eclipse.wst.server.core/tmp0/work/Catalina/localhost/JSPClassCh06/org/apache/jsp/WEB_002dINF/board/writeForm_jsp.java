/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.87
 * Generated at: 2024-04-11 11:29:28 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.board;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;
import java.util.*;
import com.jspstudy.ch06.vo.*;

public final class writeForm_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(4);
    _jspx_dependants.put("/WEB-INF/lib/taglibs-standard-impl-1.2.5.jar", Long.valueOf(1712220500000L));
    _jspx_dependants.put("/WEB-INF/board/../pages/header.jsp", Long.valueOf(1712803946000L));
    _jspx_dependants.put("/WEB-INF/board/../pages/footer.jsp", Long.valueOf(1712804019000L));
    _jspx_dependants.put("jar:file:/Users/hyeongcheol/Desktop/JSPStudy/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/JSPClassCh06/WEB-INF/lib/taglibs-standard-impl-1.2.5.jar!/META-INF/c.tld", Long.valueOf(1425946270000L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.LinkedHashSet<>(6);
    _jspx_imports_packages.add("java.sql");
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("java.util");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("com.jspstudy.ch06.vo");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSP들은 오직 GET, POST 또는 HEAD 메소드만을 허용합니다. Jasper는 OPTIONS 메소드 또한 허용합니다.");
        return;
      }
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"ko\" data-bs-theme=\"dark\">\n");
      out.write("<head>\n");
      out.write("<meta charset=\"UTF-8\">\n");
      out.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");
      out.write("<title>🧛🏻 Board List 🧛🏻</title>\n");
      out.write("<link href=\"bootstrap/bootstrap.min.css\" rel=\"stylesheet\">\n");
      out.write("<script src=\"js/jquery-3.7.1.min.js\"></script>\n");
      out.write("<script src=\"js/formCheck02.js\"></script>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("	<div class=\"container\">\n");
      out.write("		<!-- header start -->\n");
      out.write("		");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("<meta charset=\"UTF-8\">\n");
      out.write("<title>Insert title here</title>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("	<div class=\"row border-bottom border-danger-subtle pt-3\">\n");
      out.write("			<div class=\"col-4\">\n");
      out.write("				<img\n");
      out.write("					src=\"https://via.placeholder.com/200X100/DC3645/ffffff ?Text=Digital.com\">️\n");
      out.write("			</div>\n");
      out.write("			<div class=\"col-8\">\n");
      out.write("				<div class=\"row\">\n");
      out.write("					<div class=\"col\">\n");
      out.write("						<ul class=\"nav justify-content-end\">\n");
      out.write("							<li class=\"nav-item\"><a class=\"nav-link active link-danger\"\n");
      out.write("								aria-current=\"page\" href=\"#\">Login</a></li>\n");
      out.write("							<li class=\"nav-item\"><a class=\"nav-link link-danger\"\n");
      out.write("								href=\"boardList\">BoardList</a></li>\n");
      out.write("							<li class=\"nav-item\"><a class=\"nav-link link-danger\"\n");
      out.write("								href=\"#\">CreateAccount</a></li>\n");
      out.write("							<li class=\"nav-item\"><a class=\"nav-link link-danger\"\n");
      out.write("								href=\"#\">Order / Delivery</a></li>\n");
      out.write("							<li class=\"nav-item\"><a class=\"nav-link link-danger\"\n");
      out.write("								href=\"#\">Contact us</a></li>\n");
      out.write("						</ul>\n");
      out.write("					</div>\n");
      out.write("				</div>\n");
      out.write("				️\n");
      out.write("				<div class=\"row\">\n");
      out.write("					<div class=\"col text-end me-3\">Login Comment</div>\n");
      out.write("				</div>\n");
      out.write("				️\n");
      out.write("			</div>\n");
      out.write("		</div>\n");
      out.write("</body>\n");
      out.write("</html>");
      out.write("\n");
      out.write("		<!-- header end -->\n");
      out.write("\n");
      out.write("		<!-- content start -->\n");
      out.write("		<div class=\"row my-5\" id=\"global-content\">\n");
      out.write("			<div class=\"col\">\n");
      out.write("				<div class=\"row\">\n");
      out.write("					<div class=\"col\">\n");
      out.write("						<H1 class=\"text-center fw-bold fs-3\">🧛🏻 Write Post 🧛🏻</H1>\n");
      out.write("					</div>\n");
      out.write("				</div>\n");
      out.write("				\n");
      out.write("				<form name=\"writeForm\" id=\"writeForm\" action=\"writeProcess\"\n");
      out.write("					method=\"post\" class=\"row border-danger g-3 my-3\">\n");
      out.write("					<div class=\"col-md-4 offset-2\">\n");
      out.write("						<label for=\"writer\" class=\"form-label\">Writer</label> <input type=\"text\" class=\"form-control\" id=\"writer\" name=\"writer\" placeholder=\"write user name!\">\n");
      out.write("					</div>\n");
      out.write("					<div class=\"col-md-4\">\n");
      out.write("						<label for=\"pass\" class=\"form-label\">Password</label> <input type=\"password\" class=\"form-control\" id=\"pass\" name=\"pass\" placeholder=\"put your password!\">\n");
      out.write("					</div>\n");
      out.write("					<div class=\"col-8 offset-2\">\n");
      out.write("						<label for=\"title\" class=\"form-label\">Title</label> <input type=\"text\" class=\"form-control\" id=\"title\" name=\"title\">\n");
      out.write("					</div>\n");
      out.write("					<div class=\"col-8 offset-2\">\n");
      out.write("						<label for=\"title\" class=\"form-label\">Content</label> <textarea class=\"form-control\" id=\"content\" name=\"content\" rows=\"10\"></textarea>\n");
      out.write("					</div>\n");
      out.write("					<div class=\"col-8 offset-2 text-center mt-5\">\n");
      out.write("						<input type=\"submit\" value=\"Post\" class=\"btn btn-danger\">\n");
      out.write("						&nbsp;&nbsp;\n");
      out.write("						<input type=\"button\" value=\"List\" class=\"btn btn-danger\" onclick=\"location.href='boardList'\">\n");
      out.write("					</div>\n");
      out.write("				</form>\n");
      out.write("\n");
      out.write("			</div>\n");
      out.write("		</div>\n");
      out.write("		<!-- content end -->\n");
      out.write("\n");
      out.write("		<!-- footer start -->\n");
      out.write("		");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("<meta charset=\"UTF-8\">\n");
      out.write("<title>Insert title here</title>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("	<div class=\"row my-5 border-top border-danger-subtle\"\n");
      out.write("			id=\"global-footer\">\n");
      out.write("			<!-- footer -->\n");
      out.write("\n");
      out.write("			<div class=\"col-3\"></div>\n");
      out.write("\n");
      out.write("			<div class=\"col-6 text-center py-3\">\n");
      out.write("				<p class=\"text-start\">\n");
      out.write("					COMPANY. (주)또치컴퍼니<br> OWNER. 이또치 TEL. 02-7777-7777<br>\n");
      out.write("					ADDRESS. 08787 서울특별시 관악구 남부순환로 1820 (봉천동, 에그엘로우 6층)<br>\n");
      out.write("					BUSINESS LICENSE. 777-77-77777 <br>\n");
      out.write("					E-MAIL. ddochiisrich@gmail.com HOSTING BY DDOCHI(주)<br> \n");
      out.write("					COPYRIGHT (C) (주)또치컴퍼니 ALL RIGHTS RESERVED. DESIGN BY 이또치.<br>\n");
      out.write("				</p>\n");
      out.write("				️\n");
      out.write("			</div>\n");
      out.write("\n");
      out.write("			<div class=\"col-3\"></div>\n");
      out.write("\n");
      out.write("		</div>\n");
      out.write("</body>\n");
      out.write("</html>");
      out.write("\n");
      out.write("		<!-- footer end -->\n");
      out.write("		<script src=\"bootstrap/bootstrap.bundle.min.js\"></script>\n");
      out.write("	</div>\n");
      out.write("</body>\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
