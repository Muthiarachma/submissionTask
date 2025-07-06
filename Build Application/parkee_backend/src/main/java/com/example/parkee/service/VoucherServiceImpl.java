package com.example.parkee.service;

import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.parkee.model.constant.ErrorMessage;
import com.example.parkee.model.entity.Voucher;
import com.example.parkee.repository.VoucherRepository;

import static com.example.parkee.util.ValidatorUtil.validateRequest;

@Service
public class VoucherServiceImpl implements VoucherService {

  @Autowired
  private VoucherRepository voucherRepository;

  @Override
  public Voucher findValidVoucherById(String id) {

    validateRequest(StringUtils.isNotBlank(id), ErrorMessage.VOUCHER_ID_MUST_NOT_BE_BLANK);
    return voucherRepository.findValidVoucherByIdAndMarkForDeleteFalse(id);
  }
}
