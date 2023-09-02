package com.pw.system.modules.plugin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.pw.base.api.api.dto.PagingReqDTO;
import com.pw.base.utils.BeanMapper;
import com.pw.base.utils.jackson.JsonHelper;
import com.pw.system.modules.plugin.dto.PluginDataDTO;
import com.pw.system.modules.plugin.entity.PluginData;
import com.pw.system.modules.plugin.mapper.PluginDataMapper;
import com.pw.system.modules.plugin.service.PluginDataService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* <p>
* 插件信息业务实现类
* </p>
*
* @author 聪明笨狗
* @since 2022-09-05 10:05
*/
@Service
public class PluginDataServiceImpl extends ServiceImpl<PluginDataMapper, PluginData> implements PluginDataService {

    @Override
    public IPage<PluginDataDTO> paging(PagingReqDTO<PluginDataDTO> reqDTO) {

        //查询条件
        QueryWrapper<PluginData> wrapper = new QueryWrapper<>();

        // 请求参数
        PluginDataDTO params = reqDTO.getParams();

        //获得数据
        IPage<PluginData> page = this.page(reqDTO.toPage(), wrapper);
        //转换结果
        IPage<PluginDataDTO> pageData = JsonHelper.parseObject(page, new TypeReference<Page<PluginDataDTO>>(){});
        return pageData;
    }


    @Override
    public void save(PluginDataDTO reqDTO){
        //复制参数
        PluginData entity = new PluginData();
        BeanMapper.copy(reqDTO, entity);
        this.saveOrUpdate(entity);
    }

    @Override
    public void delete(List<String> ids){
        //批量删除
        this.removeByIds(ids);
    }

    @Override
    public PluginDataDTO detail(String id){
        PluginData entity = this.getById(id);
        PluginDataDTO dto = new PluginDataDTO();
        BeanMapper.copy(entity, dto);
        return dto;
    }

    @Override
    public List<PluginDataDTO> list(PluginDataDTO reqDTO){

        //分页查询并转换
        QueryWrapper<PluginData> wrapper = new QueryWrapper<>();

        //转换并返回
        List<PluginData> list = this.list(wrapper);

        //转换数据
        List<PluginDataDTO> dtoList = BeanMapper.mapList(list, PluginDataDTO.class);

        return dtoList;
    }
}
