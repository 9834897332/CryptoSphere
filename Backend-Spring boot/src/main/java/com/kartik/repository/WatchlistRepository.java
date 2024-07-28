package com.kartik.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kartik.model.Watchlist;

public interface WatchlistRepository extends JpaRepository<Watchlist,Long> {

    Watchlist findByUserId(Long userId);

}
