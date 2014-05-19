package net.smktarunabhakti.penjualan.ui.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import net.smktarunabhakti.penjualan.service.AppService;
import net.smktarunabhakti.penjualan.domain.Barang;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.net.URI;
import org.springframework.web.util.UriTemplate;

/**
 *
 * @author Admin
 */
@Controller
public class BarangController {
    @Autowired
    private AppService appService;
    
    @RequestMapping(value = "/barang",method = RequestMethod.GET)
    @ResponseBody
    public List<Barang> findAll(Pageable pageable,HttpServletResponse response){
        List<Barang> hasil = appService.cariSemuaBarang(pageable).getContent();
        return hasil;
    }
    
    @RequestMapping("/barang/{id}")
    @ResponseBody
    public Barang findbyId(@PathVariable String id){
        Barang barang = appService.cariBarangById(id);
        if (barang==null){
            throw new IllegalStateException();
        }
        return barang;
    }
    
    @RequestMapping(value="/barang",method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid Barang x,HttpServletRequest request,HttpServletResponse response){
        appService.simpanBarang(x);
        String requestUrl = request.getRequestURL().toString();
        URI uri = new UriTemplate("{requestUrl}/{id}").expand(requestUrl,x.getId());
        response.setHeader("Location", uri.toASCIIString());
    }
    
    @RequestMapping(method=RequestMethod.PUT,value = "/barang/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable String id,@RequestBody @Valid Barang b){
        Barang barang = appService.cariBarangById(id);
        if (barang==null){
            throw new IllegalStateException();
        }
        b.setId(barang.getId());
        appService.simpanBarang(b);
    }
    
    
    @RequestMapping(method=RequestMethod.DELETE,value = "/barang/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable String id,@RequestBody @Valid Barang b){
        Barang barang = appService.cariBarangById(id);
        if (barang==null){
            throw new IllegalStateException();
        }
        b.setId(barang.getId());
        appService.hapusBarang(b);
    }
}