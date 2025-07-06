package com.example.parkee.service;

import com.example.parkee.model.entity.Voucher;

public interface VoucherService {

  Voucher findValidVoucherById(String id);
}
