package org.example.yolofarmbe.Service;

import org.example.yolofarmbe.Entity.*;
import org.example.yolofarmbe.Exception.ResourceNotFoundException;
import org.example.yolofarmbe.Factory.IrrigationSettingFactory;
import org.example.yolofarmbe.Repository.FarmRepository;
import org.example.yolofarmbe.Repository.IrrigationAutomatedRepository;
import org.example.yolofarmbe.Repository.IrrigationManualRepository;
import org.example.yolofarmbe.Repository.IrrigationScheduledRepository;
import org.example.yolofarmbe.Request.IrrigationSettingRequest;
import org.example.yolofarmbe.Response.IrrigationSettingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class IrrigationSettingService {
    @Autowired
    private IrrigationSettingFactory irrigationSettingFactory;

    @Autowired
    private FarmRepository farmRepository;

    public List<? extends IrrigationSetting> getAllIrigationSetting(String mode){
        switch (mode.toLowerCase()){
            case "automated":
                IrrigationAutomatedRepository irrigationAutomatedRepository = irrigationSettingFactory.getIrrigationSettingRepository(IrrigationAutomatedRepository.class);
                return irrigationAutomatedRepository.findAll();
            case "manual":
                IrrigationManualRepository irrigationManualRepository = irrigationSettingFactory.getIrrigationSettingRepository(IrrigationManualRepository.class);
                return irrigationManualRepository.findAll();
            case "scheduled":
                IrrigationScheduledRepository irrigationScheduledRepository = irrigationSettingFactory.getIrrigationSettingRepository(IrrigationScheduledRepository.class);
                return irrigationScheduledRepository.findAll();
            default:
                throw new IllegalArgumentException("Invalid irrigation setting mode: " + mode);
        }
    }

    public IrrigationSettingResponse<? extends IrrigationSetting> getAllIrigationSettingById(String mode, int id){
        switch (mode.toLowerCase()){
            case "automated":
                IrrigationAutomatedRepository irrigationAutomatedRepository = irrigationSettingFactory.getIrrigationSettingRepository(IrrigationAutomatedRepository.class);
                IrrigationAutomated irrigationAutomated = irrigationAutomatedRepository.findById(id)
                        .orElseThrow(()->new ResourceNotFoundException("Irrigation Setting with id "+id+" doesn't exist!"));
                return IrrigationSettingResponse.builder()
                        .irrigationSetting(irrigationAutomated)
                        .message("Get irrigation setting successfully!")
                        .mode(mode)
                        .build();
            case "manual":
                IrrigationManualRepository irrigationManualRepository = irrigationSettingFactory.getIrrigationSettingRepository(IrrigationManualRepository.class);
                IrrigationManual irrigationManual = irrigationManualRepository.findById(id)
                        .orElseThrow(()->new ResourceNotFoundException("Irrigation Setting with id "+id+" doesn't exist!"));
                return IrrigationSettingResponse.builder()
                        .irrigationSetting(irrigationManual)
                        .message("Get irrigation setting successfully!")
                        .mode(mode)
                        .build();
            case "scheduled":
                IrrigationScheduledRepository irrigationScheduledRepository = irrigationSettingFactory.getIrrigationSettingRepository(IrrigationScheduledRepository.class);
                IrrigationScheduled irrigationScheduled = irrigationScheduledRepository.findById(id)
                    .orElseThrow(()->new ResourceNotFoundException("Irrigation Setting with id "+id+" doesn't exist!"));
                return IrrigationSettingResponse.builder()
                        .irrigationSetting(irrigationScheduled)
                        .message("Get irrigation setting successfully!")
                        .mode(mode)
                        .build();
            default:
                throw new IllegalArgumentException("Invalid irrigation setting mode: " + mode);
        }
    }

    public IrrigationSettingResponse<? extends IrrigationSetting> getAllIrigationSettingByFarm(String mode, int farm_id){
        Farm farm = farmRepository.findById(farm_id)
                .orElseThrow(()->new ResourceNotFoundException("Farm with id "+farm_id+"doesn't exist!"));
        switch (mode.toLowerCase()){
            case "automated":
                IrrigationAutomatedRepository irrigationAutomatedRepository = irrigationSettingFactory.getIrrigationSettingRepository(IrrigationAutomatedRepository.class);
                IrrigationAutomated irrigationAutomated = irrigationAutomatedRepository.findByFarm_Id(farm_id);
                return IrrigationSettingResponse.builder()
                        .irrigationSetting(irrigationAutomated)
                        .message("Get irrigation setting successfully!")
                        .mode(mode)
                        .build();
            case "manual":
                IrrigationManualRepository irrigationManualRepository = irrigationSettingFactory.getIrrigationSettingRepository(IrrigationManualRepository.class);
                IrrigationManual irrigationManual = irrigationManualRepository.findByFarm_Id(farm_id);
                return IrrigationSettingResponse.builder()
                        .irrigationSetting(irrigationManual)
                        .message("Get irrigation setting successfully!")
                        .mode(mode)
                        .build();
            case "scheduled":
                IrrigationScheduledRepository irrigationScheduledRepository = irrigationSettingFactory.getIrrigationSettingRepository(IrrigationScheduledRepository.class);
                IrrigationScheduled irrigationScheduled = irrigationScheduledRepository.findByFarm_Id(farm_id);
                return IrrigationSettingResponse.builder()
                        .irrigationSetting(irrigationScheduled)
                        .message("Get irrigation setting successfully!")
                        .mode(mode)
                        .build();
            default:
                throw new IllegalArgumentException("Invalid irrigation setting mode: " + mode);
        }
    }

    public IrrigationSettingResponse<? extends IrrigationSetting> addAIrigationSetting(String mode, IrrigationSettingRequest irrigationSettingRequest){
        switch (mode.toLowerCase()){
            case "automated":
                IrrigationAutomatedRepository irrigationAutomatedRepository = irrigationSettingFactory.getIrrigationSettingRepository(IrrigationAutomatedRepository.class);
                IrrigationAutomated irrigationAutomated = new IrrigationAutomated();
                if(irrigationSettingRequest.getFarm()!=null){
                    int farm_id = irrigationSettingRequest.getFarm().getId();
                    Farm farm = farmRepository.findById(farm_id)
                            .orElseThrow(()->new ResourceNotFoundException("Farm with id "+farm_id+"doesn't exist!"));
                    irrigationAutomated.setFarm(irrigationSettingRequest.getFarm());
                }
                if(irrigationSettingRequest.getMoistureLevel()!=null){
                    irrigationAutomated.setMoistureLevel(irrigationSettingRequest.getMoistureLevel());
                }
                if(irrigationSettingRequest.getDangerSafeBehavior()!=null){
                    irrigationAutomated.setDangerSafeBehavior(irrigationSettingRequest.getDangerSafeBehavior());
                }
                irrigationAutomatedRepository.save(irrigationAutomated);
                return IrrigationSettingResponse.builder()
                        .irrigationSetting(irrigationAutomated)
                        .message("Add irrigation setting successfully!")
                        .mode(mode)
                        .build();
            case "manual":
                IrrigationManualRepository irrigationManualRepository = irrigationSettingFactory.getIrrigationSettingRepository(IrrigationManualRepository.class);
                IrrigationManual irrigationManual = new IrrigationManual();
                if(irrigationSettingRequest.getFarm()!=null){
                    int farm_id = irrigationSettingRequest.getFarm().getId();
                    Farm farm = farmRepository.findById(farm_id)
                            .orElseThrow(()->new ResourceNotFoundException("Farm with id "+farm_id+"doesn't exist!"));
                    irrigationManual.setFarm(irrigationSettingRequest.getFarm());
                }
                if(irrigationSettingRequest.getWatering()!=null){
                    irrigationManual.setWatering(irrigationSettingRequest.getWatering());
                }
                irrigationManualRepository.save(irrigationManual);
                return IrrigationSettingResponse.builder()
                        .irrigationSetting(irrigationManual)
                        .message("Add irrigation setting successfully!")
                        .mode(mode)
                        .build();
            case "scheduled":
                IrrigationScheduledRepository irrigationScheduledRepository = irrigationSettingFactory.getIrrigationSettingRepository(IrrigationScheduledRepository.class);
                IrrigationScheduled irrigationScheduled = new IrrigationScheduled();
                if(irrigationSettingRequest.getFarm()!=null){
                    int farm_id = irrigationSettingRequest.getFarm().getId();
                    Farm farm = farmRepository.findById(farm_id)
                            .orElseThrow(()->new ResourceNotFoundException("Farm with id "+farm_id+"doesn't exist!"));
                    irrigationScheduled.setFarm(irrigationSettingRequest.getFarm());
                }
                if(irrigationSettingRequest.getDangerSafeBehavior()!=null){
                    irrigationScheduled.setDangerSafeBehavior(irrigationSettingRequest.getDangerSafeBehavior());
                }
                irrigationScheduledRepository.save(irrigationScheduled);
                return IrrigationSettingResponse.builder()
                        .irrigationSetting(irrigationScheduled)
                        .message("Add irrigation setting successfully!")
                        .mode(mode)
                        .build();
            default:
                throw new IllegalArgumentException("Invalid irrigation setting mode: " + mode);
        }
    }

    public IrrigationSettingResponse<? extends IrrigationSetting> updateAIrigationSetting(String mode,int id, IrrigationSettingRequest irrigatingSettingRequest){
        switch (mode.toLowerCase()){
            case "automated":
                IrrigationAutomatedRepository irrigationAutomatedRepository = irrigationSettingFactory.getIrrigationSettingRepository(IrrigationAutomatedRepository.class);
                IrrigationAutomated irrigationAutomated = irrigationAutomatedRepository.findById(id)
                        .orElseThrow(()->new ResourceNotFoundException("Irrigation Setting with id "+id+" doesn't exist!"));
                if(irrigatingSettingRequest.getFarm()!=null){
                    int farm_id = irrigatingSettingRequest.getFarm().getId();
                    Farm farm = farmRepository.findById(farm_id)
                            .orElseThrow(()->new ResourceNotFoundException("Farm with id "+farm_id+"doesn't exist!"));
                    irrigationAutomated.setFarm(irrigatingSettingRequest.getFarm());
                }
                if(irrigatingSettingRequest.getMoistureLevel()!=null){
                    irrigationAutomated.setMoistureLevel(irrigatingSettingRequest.getMoistureLevel());
                }
                if(irrigatingSettingRequest.getDangerSafeBehavior()!=null){
                    irrigationAutomated.setDangerSafeBehavior(irrigatingSettingRequest.getDangerSafeBehavior());
                }
                irrigationAutomatedRepository.save(irrigationAutomated);
                return IrrigationSettingResponse.builder()
                        .irrigationSetting(irrigationAutomated)
                        .message("Update irrigation setting successfully!")
                        .mode(mode)
                        .build();
            case "manual":
                IrrigationManualRepository irrigationManualRepository = irrigationSettingFactory.getIrrigationSettingRepository(IrrigationManualRepository.class);
                IrrigationManual irrigationManual = irrigationManualRepository.findById(id)
                        .orElseThrow(()->new ResourceNotFoundException("Irrigation Setting with id "+id+" doesn't exist!"));
                if(irrigatingSettingRequest.getFarm()!=null){
                    int farm_id = irrigatingSettingRequest.getFarm().getId();
                    Farm farm = farmRepository.findById(farm_id)
                            .orElseThrow(()->new ResourceNotFoundException("Farm with id "+farm_id+"doesn't exist!"));
                    irrigationManual.setFarm(irrigatingSettingRequest.getFarm());
                }
                if(irrigatingSettingRequest.getWatering()!=null){
                    irrigationManual.setWatering(irrigatingSettingRequest.getWatering());
                }
                irrigationManualRepository.save(irrigationManual);
                return IrrigationSettingResponse.builder()
                        .irrigationSetting(irrigationManual)
                        .message("Update irrigation setting successfully!")
                        .mode(mode)
                        .build();
            case "scheduled":
                IrrigationScheduledRepository irrigationScheduledRepository = irrigationSettingFactory.getIrrigationSettingRepository(IrrigationScheduledRepository.class);
                IrrigationScheduled irrigationScheduled = irrigationScheduledRepository.findById(id)
                        .orElseThrow(()->new ResourceNotFoundException("Irrigation Setting with id "+id+" doesn't exist!"));
                if(irrigatingSettingRequest.getFarm()!=null){
                    int farm_id = irrigatingSettingRequest.getFarm().getId();
                    Farm farm = farmRepository.findById(farm_id)
                            .orElseThrow(()->new ResourceNotFoundException("Farm with id "+farm_id+"doesn't exist!"));
                    irrigationScheduled.setFarm(irrigatingSettingRequest.getFarm());
                }
                if(irrigatingSettingRequest.getDangerSafeBehavior()!=null){
                    irrigationScheduled.setDangerSafeBehavior(irrigatingSettingRequest.getDangerSafeBehavior());
                }
                irrigationScheduledRepository.save(irrigationScheduled);
                return IrrigationSettingResponse.builder()
                        .irrigationSetting(irrigationScheduled)
                        .message("Update irrigation setting successfully!")
                        .mode(mode)
                        .build();
            default:
                throw new IllegalArgumentException("Invalid irrigation setting mode: " + mode);
        }
    }

    public IrrigationSettingResponse<? extends IrrigationSetting> deleteAIrigationSetting(String mode, int id){
        switch (mode.toLowerCase()){
            case "automated":
                IrrigationAutomatedRepository irrigationAutomatedRepository = irrigationSettingFactory.getIrrigationSettingRepository(IrrigationAutomatedRepository.class);
                IrrigationAutomated irrigationAutomated = irrigationAutomatedRepository.findById(id)
                        .orElseThrow(()->new ResourceNotFoundException("Irrigation Setting with id "+id+" doesn't exist!"));
                irrigationAutomatedRepository.delete(irrigationAutomated);
                return IrrigationSettingResponse.builder()
                        .irrigationSetting(irrigationAutomated)
                        .message("Delete irrigation setting successfully!")
                        .mode(mode)
                        .build();
            case "manual":
                IrrigationManualRepository irrigationManualRepository = irrigationSettingFactory.getIrrigationSettingRepository(IrrigationManualRepository.class);
                IrrigationManual irrigationManual = irrigationManualRepository.findById(id)
                        .orElseThrow(()->new ResourceNotFoundException("Irrigation Setting with id "+id+" doesn't exist!"));
                irrigationManualRepository.delete(irrigationManual);
                return IrrigationSettingResponse.builder()
                        .irrigationSetting(irrigationManual)
                        .message("Delete irrigation setting successfully!")
                        .mode(mode)
                        .build();
            case "scheduled":
                IrrigationScheduledRepository irrigationScheduledRepository = irrigationSettingFactory.getIrrigationSettingRepository(IrrigationScheduledRepository.class);
                IrrigationScheduled irrigationScheduled = irrigationScheduledRepository.findById(id)
                        .orElseThrow(()->new ResourceNotFoundException("Irrigation Setting with id "+id+" doesn't exist!"));
                irrigationScheduledRepository.delete(irrigationScheduled);
                return IrrigationSettingResponse.builder()
                        .irrigationSetting(irrigationScheduled)
                        .message("Delete irrigation setting successfully!")
                        .mode(mode)
                        .build();
            default:
                throw new IllegalArgumentException("Invalid irrigation setting mode: " + mode);
        }
    }

}
