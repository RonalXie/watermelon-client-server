package com.xsy.service;

import com.xsy.entity.user.Played;
import com.xsy.entity.video.Video;
import com.xsy.entity.video.VideoDetailVO;
import com.xsy.entity.video.VideoVO;

import java.util.List;

/**
 * 视频(Video)表服务接口
 *
 * @author makejava
 * @since 2021-08-06 16:38:13
 */
public interface VideoService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Video queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param start 查询起始位置
     * @param pageSize  查询条数
     * @return 对象列表
     */
    List<VideoVO> queryAllByLimit(int start, int pageSize);

    /**
     * 新增数据
     *
     * @param video 实例对象
     * @return 实例对象
     */
    Video insert(Video video);

    /**
     * 修改数据
     *
     * @param video 实例对象
     * @return 实例对象
     */
    Video update(Video video);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    List<VideoVO> queryByCategory(int start, int pageSize, int category_id);

    int queryByCategoryCount(int category_id);

    int queryAllCount();

    VideoDetailVO queryDetailById(Integer id, String token);


    Played queryByUidAndVid(Integer video_id, Integer uid);

    void insertPlayed(Played played);

    void updatePlayed(Played played);
}
