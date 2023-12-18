package com.spring.shop.service;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.shop.entity.Customer;
import com.spring.shop.repository.CustomerRepository;
import com.spring.shop.request.CapNhatProfile;
import com.spring.shop.request.ChangeForm;
import com.spring.shop.request.CustomerReques;
import com.spring.shop.request.ForgetForm;
import com.spring.shop.request.RegisterForm;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom random = new SecureRandom();
    // hien thi tat ca
    public List<Customer> getAll(){
        return customerRepository.getAll();
    }

    // Tim khach hang
    public List<Customer> getAllbyFullName(String fullname){
        return customerRepository.searchByFullName('%'+fullname+'%');
    }
//     add khach hang
    public Customer add(CustomerReques reques){
        Customer customer = new Customer();
        customer.setCode(genCodeCustom());
        customer.setFullname(reques.getFullname());
        customer.setUsername(reques.getUsername());
        customer.setPassword(reques.getPassword());
        customer.setImage(reques.getImage());
        customer.setGender(reques.getGender());
        customer.setPhone(reques.getPhone());
        customer.setEmail(reques.getEmail());
        customer.setStatus(0);
        System.out.println(customer);
        return customerRepository.save(customer);
    }
    public String genCodeCustom() {
        String code = "KH00" + customerRepository.findAll().size();
        return code;
    }
    // update khach hang
    public  Customer update(Integer id , CustomerReques reques){
        Customer customer = customerRepository.getById(id);
        customer.setCode(customer.getCode());
        customer.setFullname(reques.getFullname());
        customer.setUsername(reques.getUsername());
        customer.setPassword(reques.getPassword());
        customer.setImage(reques.getImage());
        customer.setGender(reques.getGender());
        customer.setPhone(reques.getPhone());
        customer.setEmail(reques.getEmail());
        customer.setStatus(0);
        return customerRepository.save(customer);
    }
    // xoa khach hang
    public  Customer delete(Integer id ){
        Customer customer = customerRepository.getById(id);
        customer.setStatus(1);
        return  customerRepository.save(customer);
    }
    // de tai khach hang
    public Customer getById(Integer Id){
        Customer customer = customerRepository.getById(Id);
        return customer;
    }
    // de tai khach hang
    public Customer getByUsername(String username){
        Customer customer = customerRepository.getByUsername(username);
        return customer;
    }
    public String genCode(){
        // Tạo đối tượng Random
        long timestamp = Instant.now().getEpochSecond();
        String code = "KH" + timestamp;
        return code;
    }
    // đăng ký
    public Customer register(RegisterForm form){
        Customer customer = new Customer();
        customer.setCode(genCode());
        customer.setEmail(form.getEmail());
        customer.setFullname(form.getFullname());
        customer.setUsername(form.getUsername());
        customer.setPassword(form.getPassword());
        customer.setCreateDate(new Date());
        customer.setStatus(0);
        return customerRepository.save(customer);
    }
    // đổi mật khẩu
    public Customer change(Integer idCustomer,ChangeForm form){
        Customer customer = customerRepository.getById(idCustomer);
        customer.setPassword(form.getRePasswordMoi());
        customer.setUpdateDate(new Date());
        return customerRepository.save(customer);
    }

    //cập nhật profile
    public Customer updateprofile(Integer idCustomer, CapNhatProfile form){
        Customer customer = customerRepository.getById(idCustomer);
        customer.setFullname(form.getFullname());
        customer.setEmail(form.getEmail());
        customer.setPhone(form.getPhone());
        customer.setGender(form.getGender());
        customer.setImage(form.getImage());
        customer.setUpdateDate(new Date());
        return customerRepository.save(customer);
    }

    public static String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            sb.append(randomChar);
        }
        return sb.toString();
    }
    // quên mật khẩu
    public Customer forget(ForgetForm form){
        Customer customer = customerRepository.getByUsername(form.getUsername());
        customer.setPassword(generateRandomString(8));
        customer.setUpdateDate(new Date());
        return customerRepository.save(customer);
    }
    //check đk đánh giá
    public Customer checkdk(Integer IdCustomer ,Integer IdProductDetail){
        return customerRepository.checkDanhGia(IdCustomer,IdProductDetail);
    }

    public Customer restore(Integer Id){
        Customer Customer = customerRepository.getById(Id);
        Customer.setStatus(0);
        return customerRepository.save(Customer);
    }

    public Customer deleteFake(Integer Id){
        Customer Customer = customerRepository.getById(Id);
        Customer.setStatus(1);
        return customerRepository.save(Customer);
    }

    public List<Customer> getInActive() {
        return customerRepository.getInActive();
    }
}
