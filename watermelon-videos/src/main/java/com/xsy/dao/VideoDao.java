package com.xsy.dao;

import com.xsy.entity.user.Played;
import com.xsy.entity.video.Video;
import com.xsy.entity.video.VideoVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 视频(Video)表数据库访问层
 *
 * @author makejava
 * @since 2021-08-06 16:38:13
 */
@Mapper
public interface VideoDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Video queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param start 查询起始位置
     * @param pageSize  查询条数
     * @return 对象列表
     */
    List<VideoVO> queryAllByLimit(Integer start, Integer pageSize);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param video 实例对象
     * @return 对象列表
     */
    List<Video> queryAll(Video video);

    /**
     * 新增数据
     *
     * @param video 实例对象
     * @return 影响行数
     */
    int insert(Video video);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Video> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Video> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Video> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<Video> entities);

    /**
     * 修改数据
     *
     * @param video 实例对象
     * @return 影响行数
     */
    int update(Video video);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    List<VideoVO> queryByCategory(int start, int pageSize, int category_id);

    int queryByCategoryCount(int category_id);

    int queryAllCount();

    Played queryByUidAndVid(Integer video_id, Integer uid);

    void insertPlayed(Played played);

    void updatePlayed(Played played);
}

