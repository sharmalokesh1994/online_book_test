package com.rig.book.repos;

import com.rig.book.entity.OrderEntity;
import com.rig.book.model.StasticModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity,Long> {

    @Query(value = "from OrderEntity where orderDate BETWEEN :startDate AND :endDate")
    public List<OrderEntity> getAllBetweenDates(@Param("startDate") Date startDate, @Param("endDate")Date endDate);

}
