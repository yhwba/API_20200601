package kr.co.tjoeun.apipractice_20200527.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import kr.co.tjoeun.apipractice_20200527.R;
import kr.co.tjoeun.apipractice_20200527.datas.TopicReply;

public class TopicReplyAdapter extends ArrayAdapter<TopicReply> {


    Context mContext;
    List<TopicReply> mList;
    LayoutInflater inf;

    public TopicReplyAdapter(@NonNull Context context, int resource, @NonNull List<TopicReply> objects) {
        super(context, resource, objects);
        mContext = context;
        mList = objects;
        inf = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;

        if (row == null) {
            row = inf.inflate(R.layout.topic_reply_list_item, null);
        }

        TextView contentTxt = row.findViewById(R.id.contentTxt);
        TextView writerNickNameTxt = row.findViewById(R.id.writerNickNameTxt);
        TextView sideTxt = row.findViewById(R.id.sideTxt);

        TopicReply data = mList.get(position);

        contentTxt.setText(data.getContent());
        writerNickNameTxt.setText(data.getWriter().getNickName());

        sideTxt.setText(data.getSide());

        if (data.getSide().equals("찬성")) {
            sideTxt.setTextColor(Color.RED);
            sideTxt.setBackgroundResource(R.drawable.red_border_box);
        }
        else {
            sideTxt.setTextColor(Color.BLUE);
            sideTxt.setBackgroundResource(R.drawable.blue_border_box);
        }

//        언제 댓글을 남겻는지 표시
//         댓글 남긴 시간 파싱~

        return row;
    }
}
