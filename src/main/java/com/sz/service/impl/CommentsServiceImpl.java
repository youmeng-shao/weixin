package com.sz.service.impl;

import com.sz.mapper.CommentsMapper;
import com.sz.pojo.Comments;
import com.sz.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("commentsService")
public class CommentsServiceImpl implements CommentsService {

    @Autowired
    private CommentsMapper commentsMapper;

    @Override
    public Integer saveComments(Comments comments) {

        return commentsMapper.insertComments(comments);
    }

    @Override
    public List<Comments> findAllUserComments() {

        return commentsMapper.selectAllUserComments();
    }
}
