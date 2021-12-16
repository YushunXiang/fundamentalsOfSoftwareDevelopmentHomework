package com.nwpu.pet.service;

import com.nwpu.pet.entity.Pet;

public interface IPetService {
    /**
     * 添加宠物信息
     * @param pet
     */
    void add(Pet pet);
    /**
     * 删除宠物信息
     * @param num :宠物编号
     */
    void removeByNum(String num);
    /**
     * 修改宠物信息
     * 注意：pet对象应该有宠物的num信息
     * @param pet
     */
    void update(Pet pet);
    /**
     * 根据宠物编号查询宠物对象
     * @param num
     * @return
     */
    Pet findPetByNum(String num);
}
