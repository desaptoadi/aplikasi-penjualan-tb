package net.smktarunabhakti.penjualan.service;

import net.smktarunabhakti.penjualan.domain.Barang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AppService {

	void simpanBarang(Barang b);

	void hapusBarang(Barang b);

	Page<Barang> cariSemuaBarang
	(Pageable p);

	Barang cariBarangById(String id);

	Long counSemuaBarang();

    public Object cariSemuaBarang(java.awt.print.Pageable pageable);

}