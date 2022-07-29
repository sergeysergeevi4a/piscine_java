/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Server.java                                        :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: kferterb <kferterb@student.21-school.ru    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2022/07/29 18:02:11 by kferterb          #+#    #+#             */
/*   Updated: 2022/07/29 18:02:12 by kferterb         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package edu.school21.tanks.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import edu.school21.tanks.objects.Bullet;
import edu.school21.tanks.objects.Tanks;
import edu.school21.tanks.services.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Component
public class Server {
    @Autowired
    private StatService statService;

    public static LinkedList<ClientListener> clientListeners = new LinkedList<>();
    private final LinkedList<Bullet> bulletList1;
    private final LinkedList<Bullet> bulletList2;
    private final Tanks first;
    private final Tanks second;
    private static boolean end = true;

    public Server(){
        bulletList1 = new LinkedList<>();
        bulletList2 = new LinkedList<>();
        first = new Tanks(1, 521, 100);
        second = new Tanks(2, 521, 900);

    }

    public void start(int port) {
        Players player1 = new Players(first, bulletList1);
        Players player2 = new Players(second, bulletList2);
        World output1 = new World();
        World output2 = new World();
        output1.players.add(player1);
        output1.players.add(player2);
        output2.players.add(player2);
        output2.players.add(player1);
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            Socket socket = serverSocket.accept();
            clientListeners.add(new ClientListener(socket, first, second, 1, bulletList1, statService));
            socket = serverSocket.accept();
            clientListeners.add(new ClientListener(socket, second, first, 2, bulletList2, statService));
            clientListeners.get(0).start();
            clientListeners.get(1).start();
            while (end){
                for (ClientListener cl : Server.clientListeners) {
                    if(cl.getCLientId() == 2){
                        String json2 = JSON.toJSONString(output2, SerializerFeature.DisableCircularReferenceDetect);
                        cl.write(json2);
                    }
                    else{
                        String json1 = JSON.toJSONString(output1, SerializerFeature.DisableCircularReferenceDetect);
                        cl.write(json1);
                    }
                }
                Thread.sleep(50);
            }
            statService.saveStat(first, second);
        } catch (Exception ex) {
            System.err.println("Server close");
        }
        System.out.println("Server is closed!");
    }

    public static void changeEnd(){
        end = false;
    }
}

class World{
    public List<Players> players = new ArrayList<>();
}

class Players{
    private Tanks tank;
    private LinkedList<Bullet> bullets;

    public Players(Tanks tank, LinkedList<Bullet> bullets){
        this.tank = tank;
        this.bullets = bullets;
    }

    public Tanks getTank() {
        return tank;
    }

    public void setTank(Tanks tank) {
        this.tank = tank;
    }

    public LinkedList<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(LinkedList<Bullet> bullets) {
        this.bullets = bullets;
    }
}
