package com.accumulate.user;

import com.accumulate.UserService;
import com.accumulate.base.BaseTest;
import com.accumulate.resp.Response;
import com.accumulate.resp.UserModel;
import com.accumulate.utils.ObjectUtils;
import com.accumulate.utils.StringUtils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.io.*;
import java.util.List;

/**
 * Created by tjwang on 2017/1/15.
 */
public class UserConsumerTest extends BaseTest {

    private Logger logger = LoggerFactory.getLogger(UserConsumerTest.class);

    @Resource
    private UserService userService;

    @Test
    public void testCreateUser() {
        String name = "tian";
        String pwd = "Aa123.";
        createUser(name, pwd);
    }

    private void createUser(String name, String pwd) {
        Response<UserModel> resp = userService.create(name, pwd);
        logger.debug(ObjectUtils.toString(resp));
        assertTrue(resp.isSuccess());
        UserModel user = resp.getData();
        assertNotNull(user.getId());
        assertEquals(name, user.getUsername());
    }

    @Test
    public void testCreateBatchUser() throws IOException {
        String path = "E:\\temp\\accumulate\\test\\user.csv";
        File file = new File(path);
        FileReader reader = new FileReader(file);

        CSVFormat format = CSVFormat.DEFAULT;
        CSVParser parser = format.parse(reader);
        List<CSVRecord> records = parser.getRecords();
        for (CSVRecord r : records) {
            String name = r.get(0);
            String pwd = r.get(1);
            createUser(name, pwd);
        }

    }

    public static void main(String[] args) throws IOException {
        String path = "E:\\temp\\accumulate\\test\\user.csv";
        File file = new File(path);
        file.deleteOnExit();


        CSVFormat format = CSVFormat.DEFAULT;
        FileWriter out = new FileWriter(path);
        CSVPrinter printer = new CSVPrinter(out, format);
        int num = 0;
        int len = 4;

        while(++num < 10000) {
            String name = "wang" + StringUtils.formatNumStr(num, len);
            String pwd = "Aa123.";
            printer.printRecord(name, pwd);
        }
        printer.flush();


    }

}
