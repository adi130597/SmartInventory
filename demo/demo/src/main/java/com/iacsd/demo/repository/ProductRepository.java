package com.iacsd.demo.repository;

import com.iacsd.demo.model.Product;
import com.iacsd.demo.repository.custom.ProductCustomRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> , ProductCustomRepo {

	Product findByUid(String uid);
	
	Page<Product> findAll(Specification<Product> spec,Pageable pageable);
	
	Page<Product> findAllByAccountId(Long accountId, Pageable pageble);
	
	Product findByCodeAndAccountId(String productCode, Long accountId);
		
	//List<Product> findAllByProductCategoryUid(String productCategoryUid);

	List<Product> findByNameLikeAndAccountId(String productName, Long accountId);

	//@Query("Select p from Product p where p.minimumQuantity > p.availableQuantity")
	//@Query("SELECT new com.iacsd.demo.dto.bill.BillListingDto(b.uid, b.custName, b.mobileNo, b.totalAmt, b.paymentStatus, b.createdOn)  " +
	//		"FROM Bill b INNER JOIN b.account acc WHERE b.mobileNo = :mobileNumber AND acc.id = :accountId ORDER


//	@Query("SELECT new com.iacsd.demo.dto.bill.ProductInfoDTO(p.name, p.code, co.quantity, co.unit, co.rate, co.rateUnit)" +
//			"FROM CheckOut co INNER JOIN co.bill b INNER JOIN co.checkIn ci INNER JOIN ci.product p " +
//			"WHERE b.id = :billId")
//	List<ProductInfoDTO> getCheckoutProducts(Long billId);

	@Query("SELECT p FROM Product p INNER JOIN p.account acc WHERE acc.id=:accountId AND p.minimumQuantity > p.availableQuantity")
	     List<Product> findLowStockProducts(Long accountId);


	List<Product> findAll();



//	@Query("Select availableQuantity from Product p where p.uid = :prodUid")
//	Long findAvailableCount(@Param("prodUid") String prodUid);
	
}
