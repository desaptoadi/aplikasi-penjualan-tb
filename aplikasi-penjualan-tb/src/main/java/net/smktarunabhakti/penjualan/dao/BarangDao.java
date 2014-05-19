package net.smktarunabhakti.penjualan.dao;

import net.smktarunabhakti.penjualan.domain.Barang;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface BarangDao extends PagingAndSortingRepository<Barang, String>{

}