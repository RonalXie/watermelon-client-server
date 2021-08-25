package com.xsy.service.impl;

import com.xsy.dao.UserDao;
import com.xsy.entity.user.Favorite;
import com.xsy.entity.user.Played;
import com.xsy.entity.user.User;
import com.xsy.entity.video.Comment;
import com.xsy.entity.video.CommentVO;
import com.xsy.entity.video.VideoVO;
import com.xsy.service.UserService;
import com.xsy.utils.VideoClient;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户(User)表服务实现类
 *
 * @author makejava
 * @since 2021-08-06 16:37:10
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Autowired
    private VideoClient videoClient;

    @Value("${user.filepath.video}")
    private String videoPath;


    @Value("${user.filepath.cover}")
    private String coverPath;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public User queryById(Integer id) {
        return this.userDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<User> queryAllByLimit(int offset, int limit) {
        return this.userDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User insert(User user) {
        this.userDao.insert(user);
        return user;
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User update(User user) {
        this.userDao.update(user);
        return this.queryById(user.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.userDao.deleteById(id) > 0;
    }

    @Override
    public User queryByPhone(String phone) {
        return userDao.queryByPhone(phone);
    }

    @Override
    public void insertFavorite(Integer video_id, Integer id) {
        Favorite favorite=new Favorite();
        favorite.setVideoId(video_id);
        favorite.setUid(id);
        favorite.setCreatedAt(new Date());
        userDao.insertFavorite(favorite);
    }

    @Override
    public Favorite queryFavorite(Integer uid, Integer video_id) {
        return userDao.queryFavorite(uid,video_id);
    }

    @Override
    public void deleteFavorite(Integer id, Integer video_id) {
        userDao.deleteFavorite(id,video_id);

    }

    @Override
    public List<VideoVO> queryPlayed(Integer page, Integer pageSize, Integer id) {
        int start=(page-1)*pageSize;
        List<VideoVO> videoVOS=userDao.queryPagePlayed(start,pageSize,id);

        return videoVOS;
    }

    @Override
    public List<VideoVO> queryFavorites(Integer page, Integer pageSize, Integer id) {
        int start=(page-1)*pageSize;
        List<VideoVO> videoVOS=userDao.queryPageFavorite(start,pageSize,id);


        return videoVOS;
    }

    @Override
    public int queryFavoriteCount(Integer id) {
        return userDao.queryFavoriteCount(id);
    }

    @Override
    public int queryPlayedCount(Integer id) {
        return userDao.queryPlayedCount(id);
    }

    @Override
    public List<CommentVO> queryComments(Integer video_id,Integer page,Integer pageSize) {
        int start=(page-1)*pageSize;
        return userDao.queryComments(video_id,start,pageSize);
    }

    @Override
    public int queryCommentsCount(Integer video_id) {

        return userDao.queryCommentsCount(video_id);

    }

    @Override
    public void insertComments(Comment comment) {

        userDao.insertComments(comment);

    }

    @Override
    public Map<String,String> uploadVideo(MultipartFile file, String filename) throws IOException, InterruptedException {
        File fileio = new File(videoPath+filename);
        file.transferTo(fileio);
        String basename= FilenameUtils.getBaseName(filename);

        String videoUrl="http://localhost:8071/api/"+filename;
        String coverUrl="http://localhost:8071/api/"+basename+".jpg";

        videoClient.fetchframe(videoUrl,coverPath+basename+".jpg");

        Map<String,String> result=new HashMap<>();
        result.put("videoUrl",videoUrl);
        result.put("coverUrl",coverUrl);
        return result;
    }
}
