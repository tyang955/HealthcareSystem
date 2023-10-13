package com.xuda.yygh.cmn.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuda.yygh.cmn.listenter.DictListener;
import com.xuda.yygh.cmn.mapper.DictMapper;
import com.xuda.yygh.cmn.service.DictService;
import com.xuda.yygh.model.cmn.Dict;
import com.xuda.yygh.vo.cmn.DictEeVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author ：程序员徐大大
 * @description：TODO
 * @date ：2022-04-02 17:05
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {
    @Autowired
    private DictMapper dictMapper;


    //根据id查询子数据字典
    @Override
    @Cacheable(value = "dict",keyGenerator = "keyGenerator") //根据key的规则放入到缓存中
    public List<Dict> findChildData(Long id) {
        QueryWrapper<Dict> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",id);
        List<Dict> dictList = baseMapper.selectList(wrapper);
        //向list集合设置dict对象中设置hasChilden
        for (Dict dict: dictList) {
            Long dictId = dict.getId();
            boolean isChild = this.isChildren(dictId); //将子数据的放回结果放入Dict对象中，后面需要用到这个布尔值
            dict.setHasChildren(isChild);
        }
        return dictList;
    }
    //导出数据字典

    @Override
    public void exportDictData(HttpServletResponse response) {
        try {
            //设置下载信息
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
// 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = "dict";
            response.setHeader("Content-disposition", "attachment;filename="+ fileName + ".xlsx");

            //查询数据库
            List<Dict> dictList = baseMapper.selectList(null);
            //将dict变为dictvo
            List<DictEeVo> dictVoList = new ArrayList<>(dictList.size());

            for(Dict dict : dictList) {
                DictEeVo dictVo = new DictEeVo();
                BeanUtils.copyProperties(dict, dictVo);
                dictVoList.add(dictVo);
            }
            //调用方法进行写操作
            EasyExcel.write(response.getOutputStream(), DictEeVo.class).sheet("dict").doWrite(dictVoList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 导入数据字典
     * @param file
     * @error 问题：这里导入时出现的一个问题就是创建时间，修改时间，和是否删除等的删除需要进行设置
     *              如何解决，需要进行进一步改造，因为我的sql为低版本，使用不了创建修改时间的一个默认设置
     */
    @Override
    @CacheEvict(value = "dict", allEntries=true) //当数据发送变化时，将缓存中的数据清除
    public void importData(MultipartFile file) {
        try {
            EasyExcel.read(file.getInputStream(),DictEeVo.class,new DictListener(baseMapper)).sheet().doRead();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    //查询名称
    @Override
    public String getDictName(String dictCode, String value) {
        //如果dictCode为空,直接根据value查询
        if(StringUtils.isEmpty(dictCode)){
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("value",value);
            Dict dict = dictMapper.selectOne(queryWrapper);
            return dict.getName();
        }else{//如果dictCode不为空,根据dictCode和value查询
            //根据dictcode查询dict对象，目的是得到dict的id值，在根据此id值查询此id下的子id
            QueryWrapper<Dict> queryWrapper1 = new QueryWrapper();
            queryWrapper1.eq("dict_code",dictCode);
            Dict codeDict = dictMapper.selectOne(queryWrapper1);
            Long parent_id = codeDict.getId();
            //根据parentId和value值进行查询
            QueryWrapper<Dict> queryWrapper2 = new QueryWrapper();
            queryWrapper2.eq("parent_id",parent_id).eq("value",value);
            Dict finalDict = baseMapper.selectOne(queryWrapper2);
            return finalDict.getName();
        }
    }


    //根据dictCode获取下级节点
    @Override
    public List<Dict> findByDictCode(String dictCode) {
        //根据dictcode获取对应id
        Dict dict = this.getDictByDictCode(dictCode);
        //根据id获取子节点
        List<Dict> chlidData = this.findChildData(dict.getId());
        return chlidData;
    }
    private Dict getDictByDictCode(String dictCode) {
        QueryWrapper<Dict> wrapper = new QueryWrapper<>();
        wrapper.eq("dict_code",dictCode);
        Dict codeDict = baseMapper.selectOne(wrapper);
        return codeDict;
    }

    //判断id下的子节点是否有数据
    private boolean isChildren(Long id) {
        QueryWrapper<Dict> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",id);
        Integer count = baseMapper.selectCount(wrapper);
        // count > 0  0>0是返回false ,1 >0 返回true
        return count>0;
    }
}
