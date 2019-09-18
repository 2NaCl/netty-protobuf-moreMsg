package com.fyj.protobuf.nettybuf;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class TestServerHandler extends SimpleChannelInboundHandler<MyDataInfo.MyMessage> {

    //当客户端发送过来消息后，服务端的响应
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.MyMessage msg) throws Exception {

        //这里先说一下逻辑，因为我们将三个message封装在datatype里了，所以获取client数据也得这么获取。
        //首先先获取到DataType
        MyDataInfo.MyMessage.DataType dataType = msg.getDataType();

        //根据不同的message再去获取不同的msg
        if (dataType == MyDataInfo.MyMessage.DataType.PersonType) {
            MyDataInfo.Person person = msg.getPerson();

            System.out.println(person.getName());
            System.out.println(person.getAge());
            System.out.println(person.getAddress());
        } else if (dataType == MyDataInfo.MyMessage.DataType.DogType) {
            MyDataInfo.Dog dog = msg.getDog();

            System.out.println(dog.getName());
            System.out.println(dog.getAge());
        } else {
            MyDataInfo.Cat cat = msg.getCat();

            System.out.println(cat.getCity());
            System.out.println(cat.getName());

        }
    }
}
