import com.amy.pie.mongodb.MongodbApplication;
import com.amy.pie.mongodb.common.MongoUtil;
import com.amy.pie.mongodb.domain.*;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MongodbApplication.class)
public class MongoUtilTest {

    @Autowired
    private MongoUtil<RentUnitDocument> mongoUtil;

    @Test
    public void testUpdate() {

        Query query2 = new Query();
        query2.addCriteria(Criteria.where("inv_hcode").is("14785"));



        /*RentUnitDocument rentUnitDocument = new RentUnitDocument();
        rentUnitDocument.setId(new ObjectId("5c35632998260f3b3000c7d3"));
        rentUnitDocument.setIsNew(0);
        UpdateResult updateResult = mongoUtil.updateIgnoreNull(rentUnitDocument);
        System.out.println(updateResult);*/
    }


    @Test
    public void testInsert() throws InterruptedException {

        long start = System.currentTimeMillis();

        System.out.println("--------------start--------------------" + start);


        for (int i = 0; i < 1; i++) {
            //  创建线程
            Thread th = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1; j++) {
                        RentUnitDocument rentUnitDocument = packageDoc();
                        rentUnitDocument.setInvId(RandomUtils.nextLong());
                        //System.out.println(Thread.currentThread().getName() + " " + rentUnitDocument.getInvId());
                        mongoUtil.insert(rentUnitDocument);
                    }
                }
            });
            th.start();
            th.join();
        }

        System.out.println("----------------------------------" + (System.currentTimeMillis() - start));

    }

    @Test
    public void testBatchInsert() throws InterruptedException {

        long start = System.currentTimeMillis();

        System.out.println("--------------start--------------------" + start);


        for (int i = 0; i < 10; i++) {
            //  创建线程
            Thread th = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 100000; j++) {
                        RentUnitDocument rentUnitDocument = packageDoc();
                        rentUnitDocument.setInvId(RandomUtils.nextLong());
                        //System.out.println(Thread.currentThread().getName() + " " + rentUnitDocument.getInvId());
                        mongoUtil.insert(rentUnitDocument);
                    }
                }
            });
            th.start();
            th.join();
        }

        System.out.println("----------------------------------" + (System.currentTimeMillis() - start));

    }


    private RentUnitDocument packageDoc() {
        //构造对象
        RentUnitDocument rentUnitDocument = new RentUnitDocument();
        rentUnitDocument.setInvHouseCode("652312");
        rentUnitDocument.setInvRoomCode("54678");
        rentUnitDocument.setHousingStatus("404");
        rentUnitDocument.setHousingDisplayStatus("405");
        rentUnitDocument.setSalesStatus("403");
        rentUnitDocument.setVacancyDays(25L);
        rentUnitDocument.setIsNew(1);
        rentUnitDocument.setAirQuality(1);
        rentUnitDocument.setAgentEndDate(new Date());
        rentUnitDocument.setRentEndDate(new Date());
        rentUnitDocument.setCityCode("110000");


        HouseDocument.HouseDocumentBuilder houseBuilder = HouseDocument.builder();
        houseBuilder.houseId(652312L).houseCode("110308902382").houseSourceCode("BJHX290787").hireContractCode("BJSF290848");
        houseBuilder.roomId(54678L).roomCode("02").bedroomCount(3).canHasPet(0).face("南北").hasAiLock(1);
        houseBuilder.hasLift(1).pubToiletCount(0).parlorCount(1).heatMode("2030004").ratingAddress("北京市朝阳区酒仙桥将台路1号").size(12.3);
        houseBuilder.styleCode("3000325").versionId(1003L).isWhole(0).evaluation("房评信息");
        houseBuilder.hxPic("g2m1/M00/7E/82/ChAFBluOSuOAC_Y6ABy7PhIu2FE155.JPG");

        //卧室图片
        List<RoomDocument> roomPicList = Lists.newArrayList();
        RoomDocument room = new RoomDocument(54678L, "02", "南", 10.3, 0, 0, Arrays.asList("g2m1/M00/7E/82/ChAFBluOSuOAC_Y6ABy7PhIu2FE155.JPG", "g2m1/M00/7E/82/ChAFBluOSuiAHLihAB-K4q7MegY157.JPG"));
        RoomDocument room2 = new RoomDocument(54679L, "03", "北", 9.8, 0, 0, Arrays.asList("g2m1/M00/7E/82/ChAFBluOSuOAC_Y6ABy7PhIu2FE155.JPG", "g2m1/M00/7E/82/ChAFBluOSuiAHLihAB-K4q7MegY157.JPG"));
        roomPicList.add(room);
        roomPicList.add(room2);
        houseBuilder.rooms(roomPicList);

        //公区图片
        UnitPicDocument unitPic = new UnitPicDocument();
        unitPic.setKitchen(Arrays.asList("g2m1/M00/7E/82/ChAFBluOSuOAC_Y6ABy7PhIu2FE99.JPG", "g2m1/M00/7E/82/ChAFBluOSuOAC_Y6ABy7PhIu2F677.JPG"));
        unitPic.setParlour(Arrays.asList("g2m1/M00/7E/82/ChAFBluOSuOAC_Y6ABy7PhIu2FE99.JPG", "g2m1/M00/7E/82/ChAFBluOSuOAC_Y6ABy7PhIu2F677.JPG"));
        unitPic.setToilet(Arrays.asList("g2m1/M00/7E/82/ChAFBluOSuOAC_Y6ABy7PhIu2FE99.JPG", "g2m1/M00/7E/82/ChAFBluOSuOAC_Y6ABy7PhIu2F677.JPG"));
        houseBuilder.unitPic(unitPic);

        //管家
        List<ManagerDocument> houseManagerDocuments = Lists.newArrayList();

        ManagerDocument houseManager = new ManagerDocument();
        houseManager.setCode("60012859");
        houseManager.setName("张亮");
        houseManager.setType("6000589");
        houseManager.setExtendType(2);

        ManagerDocument houseManager2 = new ManagerDocument();
        houseManager2.setCode("60012859");
        houseManager2.setName("老李");
        houseManager2.setType("6000589");
        houseManager2.setExtendType(2);

        houseManagerDocuments.add(houseManager);
        houseManagerDocuments.add(houseManager2);
        houseBuilder.houseManager(houseManagerDocuments);

        //楼盘信息
        ResblockDocument resblockInfo = new ResblockDocument();
        resblockInfo.setId(1231345799994L);
        resblockInfo.setName("将府家园");
        resblockInfo.setLatitude("104.09517");
        resblockInfo.setLongitude("30.704754");
        resblockInfo.setBuildYear("2014");
        resblockInfo.setGreenRate("15");
        resblockInfo.setDistrictCode(110002L);
        resblockInfo.setDistrictName("朝阳区");
       /* resblockInfo.setBizCircleCode("115460");
        resblockInfo.setBizCircleName("798");*/

        //地铁信息
        List<SubwayDocument> subwayInfoList = Lists.newArrayList();
        SubwayDocument s = new SubwayDocument();
        s.setLine("14号线");

        List<StationDocument> stations = Lists.newArrayList();
        StationDocument station = new StationDocument();
        station.setStationName("将台");
        station.setDistance(500);
        station.setLatitude(104.09517);
        station.setLongitude(30.704754);

        StationDocument station2 = new StationDocument();
        station2.setStationName("东风北桥");
        station2.setDistance(400);
        station2.setLatitude(104.09517);
        station2.setLongitude(30.704754);

        stations.add(station);
        stations.add(station2);

        s.setStation(stations);

        SubwayDocument s2 = new SubwayDocument();
        s2.setLine("6号线");

        List<StationDocument> stationss = Lists.newArrayList();
        StationDocument station3 = new StationDocument();
        station3.setStationName("金台路");
        station3.setDistance(1500);
        station3.setLatitude(105.09517);
        station3.setLongitude(30.504754);

        stationss.add(station3);

        s2.setStation(stationss);

        subwayInfoList.add(s);
        subwayInfoList.add(s2);

        resblockInfo.setSubway(subwayInfoList);

        rentUnitDocument.setHouse(houseBuilder.build());
        rentUnitDocument.setResblock(resblockInfo);
        return rentUnitDocument;
    }

}
