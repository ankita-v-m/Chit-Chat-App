package com.ankita.dell.chit_chatapp.Common;

import com.ankita.dell.chit_chatapp.Holder.QBUsersHolder;
import com.quickblox.users.model.QBUser;

import java.util.List;

/**
 * Created by Dell on 10-May-19.
 */

public class Common {

    public static  final String DIALOG_EXTRA="Dialgos";

    public static String createChatDialogName(List<Integer> qbUsers)
    {
        List<QBUser> qbUsers1= QBUsersHolder.getInstance().getUsersByIds(qbUsers);
        StringBuilder name=new StringBuilder();
        for (QBUser user:qbUsers1)
            name.append(user.getFullName()).append(" ");
        if (name.length() > 30)
            name=name.replace(30,name.length()-1,"...");

        return name.toString();

    }
}
