package com.pla.controller;

import com.pla.Utils.LocalUploadImageUtil;
import com.pla.Manager.ResultUtils;
import com.pla.Utils.UploadPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.pla.Utils.*;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

@RestController
@RequestMapping("/UploadPicture")
public class UploadPictureController {
    @Autowired
    private UploadPictureService uploadPictureService;

    @PostMapping("/uPicture")
    public Result UploadPicture(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String imageFile = LocalUploadImageUtil.uploadImageFile(file, fileName);
        if (!imageFile.isEmpty()) {
            return ResultUtils.ok(imageFile, "ok");
        }
        return ResultUtils.fail();
    }
    JButton uploadButton;
    JPanel panel;
    public UploadPictureController () {
        uploadButton = new JButton();
        uploadButton.setText("上传图片");
        panel = new JPanel();
        panel.add(uploadButton);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        int height = (int)dimension.getHeight();
        int width = (int)dimension.getWidth();
        int x = (width - 300) / 2;
        int y = (height - 400) / 2;
        panel.setBounds(x,y,width,height);
        panel.setVisible(true);
        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = 0;
                File file = null;
                String path = null;
                JFileChooser fileChooser = new JFileChooser();
                FileSystemView fileSystemView = FileSystemView.getFileSystemView();
                System.out.println(fileSystemView.getHomeDirectory());
                fileChooser.setCurrentDirectory(fileSystemView.getHomeDirectory());
                fileChooser.setDialogTitle("请选择要上传的文件...");
                fileChooser.setApproveButtonText("确定");
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                JPanel chatFrame = new JPanel();
                chatFrame.setVisible(true);
                chatFrame.setBounds(x, y, width, height);
                result = fileChooser.showOpenDialog(chatFrame);
                if (JFileChooser.APPROVE_OPTION == result) {
                    path = fileChooser.getSelectedFile().getPath();
                    System.out.println("path is:" + path);
                }
            }
        });
    }

    public static void main(String[] args) {

        new UploadPictureController();
    }
}
