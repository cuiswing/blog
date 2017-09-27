package com.cui.blog.web.controller;

import com.cui.blog.biz.dto.CommentDTO;
import com.cui.blog.biz.service.CommentService;
import com.cui.blog.dal.po.CommentDO;
import com.cui.blog.web.form.Result;
import com.cui.blog.web.mappper.CommentMapper;
import com.cui.blog.web.utils.IPUtils;
import com.cui.blog.web.vo.CommentVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 评论处理前端控制器
 * Created by cuishixiang on 2017-09-07.
 */
@Controller
@RequestMapping("/comment")
public class CommentController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommentController.class);

    @Resource
    private CommentService commentService;

    /**
     * 保存评论
     *
     * @param request   请求对象
     * @param commentVO 评论VO
     * @return 响应数据
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Result<CommentDO> save(HttpServletRequest request, @RequestBody CommentVO commentVO) {
        Result<CommentDO> result = new Result<>();

        CommentDTO commentDTO = CommentMapper.commentVO2DTO(commentVO);
        commentDTO.setIp(IPUtils.getIpAddr(request));
        CommentDO commentDO = commentService.save(commentDTO);
        result.setData(commentDO);
        return result;
    }


    /**
     * 根据文章id获取所有的评论
     *
     * @param articleId 文章id
     * @return 查询结果集
     */
    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Result list(@RequestParam(value = "articleId") Integer articleId) {
        Result<List<CommentDO>> result = new Result<>();
        List<CommentDO> commentDOS = commentService.listAll(articleId);
        result.setData(commentDOS);
        return result;
    }

}
