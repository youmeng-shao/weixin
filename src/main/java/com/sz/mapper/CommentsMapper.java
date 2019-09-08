package com.sz.mapper;

import com.sz.pojo.Comments;

import java.util.List;

public interface CommentsMapper {

    /**
     * 保存用户提交的评论
     * @param comments
     * @return Integer
     * */
    Integer insertComments(Comments comments);

    /**
     * 查询所有用户的评论
     * @return List(Comments)
     * */
    List<Comments> selectAllUserComments();
}
