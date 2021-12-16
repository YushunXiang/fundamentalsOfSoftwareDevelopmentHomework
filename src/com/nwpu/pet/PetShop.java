package com.nwpu.pet;

import com.nwpu.map.IMap;
import com.nwpu.map.SimpleMap;
import com.nwpu.pet.entity.Pet;
import com.nwpu.pet.exception.PetException;
import com.nwpu.pet.service.IPetService;

/**
 * Desc:
 * @see IMap
 * @see SimpleMap
 * @see PetException
 * @see IPetService
 * @see Pet
 * @author Yushun Xiang
 * @date 2021/12/5 22:56
 */
public class PetShop implements IPetService {

    private IMap<String, Pet> map = new SimpleMap<>();

    @Override
    public void add(Pet pet) {
        if (pet == null) {

            throw new PetException("Pet is null exception.");
        }

        if (pet.getPetNum() == null || pet.getPetNum().equals("")) {

            throw new PetException("The PetNum is null or empty");
        }
        map.put(pet.getPetNum(), pet);
    }

    @Override
    public void removeByNum(String num) {

        if (num == null || num.equals("")) {

            throw new PetException("The PetNum is null or empty");
        }
        map.remove(num);
    }

    @Override
    public void update(Pet pet) {

        if (pet == null) {

            throw new PetException("Pet is null exception.");
        }
        if (pet.getPetNum() == null || pet.getPetNum().equals("")) {

            throw new PetException("The PetNum is null or empty");
        }
        map.set(pet.getPetNum(), pet);
    }

    @Override
    public Pet findPetByNum(String num) {

        if (num == null || num.equals("")) {

            throw new PetException("The PetNum is null or empty");
        }

        return map.get(num);
    }
}
