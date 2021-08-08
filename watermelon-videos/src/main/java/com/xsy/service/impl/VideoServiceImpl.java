package com.xsy.service.impl;

import com.xsy.dao.VideoDao;
import com.xsy.entity.user.Played;
import com.xsy.entity.RedisPrefix;
import com.xsy.entity.user.User;
import com.xsy.entity.category.Category;
import com.xsy.entity.user.UserUp;
import com.xsy.entity.video.Video;
import com.xsy.entity.video.VideoDetailVO;
import com.xsy.entity.video.VideoVO;
import com.xsy.service.VideoService;
import com.xsy.utils.CategoryClient;
import com.xsy.utils.UserClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 视频(Video)表服务实现类
 *
 * @author makejava
 * @since 2021-08-06 16:38:13
 */
@Service("videoService")
public class VideoServiceImpl implements VideoService {
    @Resource
    private VideoDao videoDao;

    @Autowired
    private UserClient userClient;

    @Autowired
    private CategoryClient categoryClient;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Video queryById(Integer id) {
        return this.videoDao.queryById(id);
    }

    /**
     * 查询多条数据
     *

     * @return 对象列表
     */
    @Override
    public List<VideoVO> queryAllByLimit(int start, int pageSize) {
        return this.videoDao.queryAllByLimit(start, pageSize);
    }

    /**
     * 新增数据
     *
     * @param video 实例对象
     * @return 实例对象
     */
    @Override
    public Video insert(Video video) {
        this.videoDao.insert(video);
        return video;
    }

    /**
     * 修改数据
     *
     * @param video 实例对象
     * @return 实例对象
     */
    @Override
    public Video update(Video video) {
        this.videoDao.update(video);
        return this.queryById(video.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.videoDao.deleteById(id) > 0;
    }

    @Override
    public List<VideoVO> queryByCategory(int start, int pageSize, int category_id) {
        return videoDao.queryByCategory(start,pageSize,category_id);
    }

    public int queryByCategoryCount(int category_id) {
        return videoDao.queryByCategoryCount(category_id);
    }

    @Override
    public int queryAllCount() {
        return videoDao.queryAllCount();
    }

    @Override
    public VideoDetailVO queryDetailById(Integer id, String token) {
        Video video=videoDao.queryById(id);
        VideoDetailVO videoDetailVO=new VideoDetailVO();
        BeanUtils.copyProperties(video,videoDetailVO);

        //处理类别信息
        Category category=categoryClient.selectOne(video.getCategoryId());
        videoDetailVO.setCategory(category.getName());
        //处理UP主信息

        User user=userClient.selectOne(video.getUid()) ;
        UserUp userUp=new UserUp();
        BeanUtils.copyProperties(user,userUp);
        videoDetailVO.setUploader(userUp);
        //播放次数
        int played_count= (int) redisTemplate.opsForValue().get(RedisPrefix.VIDEO_PLAYED_COUNT+id);
        videoDetailVO.setPalyCount(played_count);
        //点赞次数
        if (!redisTemplate.hasKey(RedisPrefix.VIDEO_LiKED_COUNT+id)){
            videoDetailVO.setLikeCount(0);
        }
        else{
            int linked_count= (int) redisTemplate.opsForValue().get(RedisPrefix.VIDEO_LiKED_COUNT+id);
            videoDetailVO.setLikeCount(linked_count);
        }
        User userLogin= (User) redisTemplate.opsForValue().get(RedisPrefix.TOKEN+token);
        //是否点赞
        if (!ObjectUtils.isEmpty(token)){

            videoDetailVO.setLiked(redisTemplate.opsForSet().isMember(RedisPrefix.USER_LIKED_ID+userLogin.getId(),id));
        }

        //是否收藏
        videoDetailVO.setFavorite(userClient.isFavorite(userLogin.getId(),id));
        return videoDetailVO;
    }

    @Override
    public Played queryByUidAndVid(Integer video_id, Integer uid) {
        return videoDao.queryByUidAndVid(video_id,uid);
    }

    @Override
    public void insertPlayed(Played played) {
        videoDao.insertPlayed(played);
    }

    @Override
    public void updatePlayed(Played played) {
        videoDao.updatePlayed(played);
    }
}
