package com.xsy.service;

import com.xsy.entity.user.Favorite;
import com.xsy.entity.user.User;
import com.xsy.entity.video.VideoVO;

import java.util.List;

/**
 * 用户(User)表服务接口
 *
 * @author makejava
 * @since 2021-08-06 16:37:09
 */
public interface UserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    User queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<User> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User insert(User user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User update(User user);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    User queryByPhone(String phone);

    void insertFavorite(Integer video_id, Integer id);

    Favorite queryFavorite(Integer uid, Integer video_id);

    void deleteFavorite(Integer id,Integer video_id);

    List<VideoVO> queryPlayed(Integer page, Integer pageSize, Integer id);

    List<VideoVO> queryFavorites(Integer page, Integer pageSize, Integer id);

    int queryFavoriteCount(Integer id);
    int queryPlayedCount(Integer id);

}
