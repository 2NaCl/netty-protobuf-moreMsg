package com.fyj.protobuf.nettybuf;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Random;

public class TestClientHandler extends SimpleChannelInboundHandler<MyDataInfo.MyMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.MyMessage msg) throws Exception {

    }


    //从客户端发给服务器端，并且通过protobuf生成的序列化去发送过去
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        //创建随机数
        int randomInt = new Random().nextInt(3);

        MyDataInfo.MyMessage myMessage = null;

        //根据随机数的不同，发送不同的消息
        if (0 == randomInt) {
            myMessage = MyDataInfo.MyMessage.newBuilder()
                    .setDataType(MyDataInfo.MyMessage.DataType.PersonType)
                    .setPerson(MyDataInfo.Person.newBuilder().setAddress("北京").setAge(18).setName("张三").build())
                    .build();
        } else if (1 == randomInt) {
            myMessage = MyDataInfo.MyMessage.newBuilder()
                    .setDataType(MyDataInfo.MyMessage.DataType.DogType)
                    .setDog(MyDataInfo.Dog.newBuilder().setAge(18).setName("dog").build())
                    .build();
        } else {
            myMessage = MyDataInfo.MyMessage.newBuilder()
                    .setDataType(MyDataInfo.MyMessage.DataType.CatType)
                    .setCat(MyDataInfo.Cat.newBuilder().setName("cat").setCity("天津").build())
                    .build();
        }
        ctx.channel().writeAndFlush(myMessage);
    }
}
