package net.smktarunabhakti.penjualan.service.impl;

import net.smktarunabhakti.penjualan.dao.BarangDao;
import net.smktarunabhakti.penjualan.domain.Barang;
import net.smktarunabhakti.penjualan.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@SuppressWarnings("unchecked")
@Service("appService")
@Transactional

public class AppServiceImpl implements AppService {

	@Autowired
	public BarangDao barangDao;

	public void simpanBarang(Barang b) {
		barangDao.save(b);

	}

	public void hapusBarang(Barang b) {
		Barang nul = null;
		if (b == nul || b.getId() == null) {
			return;
		}
		barangDao.delete(b);
	}


	public Page<Barang> cariSemuaBarang(Pageable p) {
		// TODO Auto-generated method stub
		if (p == null) {
			p = new PageRequest(0,20);
		}
		return barangDao.findAll(p);
	}

	public Barang cariBarangById(String id) {
		if (!StringUtils.hasText(id)) {
			return null;
		}
		return barangDao.findOne(id);
	}

	public Long counSemuaBarang() {
		// TODO Auto-generated method stub
		return barangDao.count();
	}

    public Object cariSemuaBarang(java.awt.print.Pageable pageable) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}