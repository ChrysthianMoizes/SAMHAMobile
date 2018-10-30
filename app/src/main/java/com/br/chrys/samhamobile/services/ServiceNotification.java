package com.br.chrys.samhamobile.services;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.greenrobot.eventbus.EventBus;

public class ServiceNotification extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage mensagem){
        EventBus.getDefault().post( mensagem );
    }
}
