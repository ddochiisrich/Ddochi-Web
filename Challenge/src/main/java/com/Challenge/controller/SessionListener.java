package com.Challenge.controller;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.Challenge.dao.ChallengeDao;

@WebListener()
public class SessionListener implements HttpSessionListener {
	 
    @Override
    public void sessionCreated(HttpSessionEvent arg0) {

        // DAO 객체 생성
        ChallengeDao dao = new ChallengeDao();
         
        // 전체 방문자 수 +1
        dao.setVisitTotalCount();

        // 전체 방문자 수
        int totalCount = dao.getVisitTotalCount();
         
        HttpSession session = arg0.getSession();
         
        // 세션 속성에 담아준다.
        session.setAttribute("totalCount", totalCount); // 전체 방문자 수
         
    }
 
    @Override
    public void sessionDestroyed(HttpSessionEvent arg0) {
 
    }
 
}

