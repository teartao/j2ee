package com.neotao.testing.services;

import com.neotao.testing.component.FileUploadComponent;
import com.neotao.testing.dao.StudentDao;
import com.neotao.testing.pojo.StudentDTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Resource
    private FileUploadComponent fileUploadComponent;

    @Resource
    private StudentDao studentDao;

    @Override
    public List<StudentDTO> queryStudent(int age) {
        return studentDao.queryList(age);
    }

    @Override
    public void uploadPhoto(StudentDTO studentDTO) {
        String url = fileUploadComponent.uploadPhoto();
        studentDTO.setPhoto(url);
        studentDao.update(studentDTO);
    }
}
