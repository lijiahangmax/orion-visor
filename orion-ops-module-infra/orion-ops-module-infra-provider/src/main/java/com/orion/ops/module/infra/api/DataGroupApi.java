package com.orion.ops.module.infra.api;

import com.orion.ops.module.infra.entity.dto.data.DataGroupCreateDTO;
import com.orion.ops.module.infra.entity.dto.data.DataGroupDTO;
import com.orion.ops.module.infra.entity.dto.data.DataGroupUpdateDTO;
import com.orion.ops.module.infra.enums.DataGroupTypeEnum;

import java.util.List;

/**
 * 数据分组 对外服务类
 *
 * @author Jiahang Li
 * @version 1.0.0
 * @since 2023-11-7 18:44
 */
public interface DataGroupApi {

    /**
     * 创建数据分组
     *
     * @param type type
     * @param dto  dto
     * @return id
     */
    Long createDataGroup(DataGroupTypeEnum type, DataGroupCreateDTO dto);

    /**
     * 重命名分组
     *
     * @param dto dto
     * @return effect
     */
    Integer renameDataGroup(DataGroupUpdateDTO dto);

    // FIXME drag

    /**
     * 通过缓存查询数据分组
     *
     * @param type type
     * @return rows
     */
    List<DataGroupDTO> getDataGroupList(DataGroupTypeEnum type);

    /**
     * 通过缓存查询数据分组
     *
     * @param type type
     * @return rows
     */
    List<DataGroupDTO> getDataGroupTree(DataGroupTypeEnum type);

    /**
     * 删除数据分组
     *
     * @param id id
     * @return effect
     */
    Integer deleteDataGroupById(Long id);

}
