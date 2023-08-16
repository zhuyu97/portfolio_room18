package com.room18.bond.controller;

import com.room18.bond.service.BondDetailService;
import com.room18.common.VO.BondVO;
import com.room18.common.entity.Bond;
import com.room18.bond.service.BondService;
import com.room18.common.R;
import com.room18.common.entity.BondDetail;
import io.swagger.annotations.Api;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.ls.LSException;

import javax.ws.rs.Path;
import java.util.ArrayList;
import java.util.List;

@Api(tags = "Bond data interface")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/bond")
public class BondController {
    @Autowired
    private BondService bondService;

    @Autowired
    private BondDetailService bondDetailService;

    @GetMapping("/getAll")
    public R getAllBonds() {
        List<Bond> bondList = bondService.getAllBonds();
        List<BondVO> bondVOList = new ArrayList<>();
        if(bondList.size() > 0){
            for(Bond bond: bondList){
                BondDetail bondDetail = bondDetailService.findBondDetailByBondId(bond.getBondId());
                BondVO bondVO = new BondVO();
                BeanUtils.copyProperties(bondDetail, bondVO);
                bondVO.setBondName(bond.getBondName());
                bondVOList.add(bondVO);
            }
            return R.ok().put("data", bondVOList);
        }
        return R.error(404, "No bonds found.");
    }

    @GetMapping("/{bondId}")
    public R getBondById(@PathVariable Long bondId) {
        Bond bond = bondService.getBondById(bondId);
        if(bond != null){
            BondDetail bondDetail = bondDetailService.findBondDetailByBondId(bond.getBondId());
            BondVO bondVO = new BondVO();
            BeanUtils.copyProperties(bondDetail, bondVO);
            bondVO.setBondName(bond.getBondName());
            return R.ok().put("data", bondVO);
        }
        else {
            return R.error(404, "There is no bond with the bondId.");
        }
    }

    @PostMapping("/")
    public R createBond(@RequestBody Bond bond) {
        return R.ok().put("data",bondService.saveBond(bond));
    }

    @PutMapping("/{bondId}")
    public R updateBond(@PathVariable Long bondId, @RequestBody Bond bond) {
        Bond existingBond = bondService.getBondById(bondId);
        if (existingBond != null) {
            // 更新现有的债券信息
            existingBond.setBondName(bond.getBondName());
            return R.ok().put("data",bondService.saveBond(existingBond));
        }
        return R.error("The bond id doesn't exist");
    }

    @DeleteMapping("/{bondId}")
    public R deleteBond(@PathVariable Long bondId) {
        bondService.deleteBond(bondId);
        return R.ok().put("message", "Successfully deleted");
    }

    @GetMapping("/fuzzyQuery/{queryString}")
    public R getBondByFuzzyQuery(@PathVariable("queryString") String queryString){
        List<Bond> bondList = bondService.findBondByFuzzyQuery(queryString);
        List<BondVO> bondVOList = new ArrayList<>();
        if(bondList.size() > 0){
            for(Bond bond: bondList){
                BondDetail bondDetail = bondDetailService.findBondDetailByBondId(bond.getBondId());
                BondVO bondVO = new BondVO();
                BeanUtils.copyProperties(bondDetail, bondVO);
                bondVO.setBondName(bond.getBondName());
                bondVOList.add(bondVO);
            }
            return R.ok().put("data", bondVOList);
        }
        return R.error(404, "No bonds found.");
    }

    // 其他控制器方法
}
