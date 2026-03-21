package com.javatechie.stock_trading_client.service;

import com.javatechie.grpc.OrderSummary;
import com.javatechie.grpc.StockRequest;
import com.javatechie.grpc.StockResponse;
import com.javatechie.grpc.StockTradingServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class StockClientService {
    @GrpcClient("stockService")
    private StockTradingServiceGrpc.StockTradingServiceStub stockTradingServiceStub;
    public void subscribeStockPrice(String stockSymbol) {
        StockRequest request = StockRequest.newBuilder().setStockSymbol(stockSymbol).build();
        stockTradingServiceStub.subscribeStockService(request, new StreamObserver<StockResponse>() {
            @Override
            public void onNext(StockResponse stockResponse) {
                System.out.println("Response :"+stockResponse.getStockSymbol()
                +","+stockResponse.getPrice()+","+stockResponse.getTimestamp());
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println(throwable.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("stock price stream live upda");
            }
        });
    }

    public void placeBulkOrders() {
        StreamObserver<OrderSummary> responseObserver = new StreamObserver<OrderSummary>() {
            @Override
            public void onNext(OrderSummary orderSummary) {
                System.out.println("Order Summary Received from Server:");
                System.out.println("Total Orders: " + orderSummary.getTotalOrders());
                System.out.println("Successful Orders: " + orderSummary.getSuccessCount());
                System.out.println("Total Amount: $" + orderSummary.getTotalAmount());
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("Order Summary Receivedn error from Server:" + throwable);
            }

            @Override
            public void onCompleted() {
                System.out.println("Stream completed , server is done se
            }
        };
        StreamObserver<StockOrder> requestObserver = new StreamObserver<StockOrder>();

    }
}
