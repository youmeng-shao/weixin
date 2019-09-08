package com.sz.service;

import com.sz.pojo.Comments;

import java.util.List;

public interface CommentsService {

    /**
     * 保存用户提交的评论
     * @param comments
     * @return Integer
     * */
    Integer saveComments(Comments comments);

    /**
     * 查询所有用户的评论
     * @return List(Comments)
     * */
    List<Comments> findAllUserComments();
}
