package com.neosoft.QuestionAnswerBank.Security.services;

import com.neosoft.QuestionAnswerBank.Dao.UserDao;
import com.neosoft.QuestionAnswerBank.Entity.User;
import com.neosoft.QuestionAnswerBank.Security.services.JwtUserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Username not found with username : " + username));

        return JwtUserDetailsImpl.build(user);
    }
}
