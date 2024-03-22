package net.technic.snow_update.entity.client.armor;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.world.entity.EquipmentSlot;

public class YetiArmorModel {
    public static class Helmet extends ArmorBaseModel{

        public Helmet(ModelPart pRoot) {
            super(pRoot, EquipmentSlot.HEAD);
            
        }

        public static LayerDefinition createBodyLayer() {
            MeshDefinition meshdefinition = HumanoidModel.createMesh(CubeDeformation.NONE, 0);
            PartDefinition partdefinition = meshdefinition.getRoot();
    
            PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(1.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
    
            head.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(33, 1).addBox(-1.0F, -1.5F, -6.0F, 2.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, -6.5F, -1.0F, -0.7854F, 0.0F, 0.0F));
            partdefinition.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);


            return LayerDefinition.create(meshdefinition, 64, 32);
        }

    }

    public static class Chestplate extends ArmorBaseModel{

        public Chestplate(ModelPart pRoot) {
            super(pRoot, EquipmentSlot.CHEST);

        }

        public static LayerDefinition createBodyLayer() {
            MeshDefinition meshdefinition = HumanoidModel.createMesh(new CubeDeformation(0.5F), 0);
            PartDefinition partdefinition = meshdefinition.getRoot();
    
            partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(1.01F)), PartPose.offset(0.0F, 0.0F, 0.0F));
    
            partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(1.0F)), PartPose.offset(-5.0F, 2.0F, 0.0F));
    
            partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(40, 16).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(1.0F)).mirror(false), PartPose.offset(5.0F, 2.0F, 0.0F));
    
            return LayerDefinition.create(meshdefinition, 64, 32);
        }
    }

    public static class Leggings extends ArmorBaseModel{

        public Leggings(ModelPart pRoot) {
            super(pRoot, EquipmentSlot.LEGS);
        }
        
        public static LayerDefinition createBodyLayer() {
            MeshDefinition meshdefinition = HumanoidModel.createMesh(new CubeDeformation(0.2F), 0);
            PartDefinition partdefinition = meshdefinition.getRoot();
    
            partdefinition.addOrReplaceChild("belt", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.51F)), PartPose.offset(0.0F, 0.0F, 0.0F));
    
            partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.5F)), PartPose.offset(-1.9F, 12.0F, 0.0F));
    
            partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.5F)).mirror(false), PartPose.offset(1.9F, 12.0F, 0.0F));
    
            return LayerDefinition.create(meshdefinition, 64, 32);
        }
    }

    public static class Boots extends ArmorBaseModel {
        
        public Boots(ModelPart pRoot){
            super(pRoot, EquipmentSlot.FEET);
        }
        
        public static LayerDefinition createBodyLayer() {
            MeshDefinition meshdefinition = HumanoidModel.createMesh(new CubeDeformation(0.6F), 0);
            PartDefinition partdefinition = meshdefinition.getRoot();
    
            partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(1.0F)).mirror(false), PartPose.offset(1.9F, 12.0F, 0.0F));
    
            partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(1.0F)), PartPose.offset(-1.9F, 12.0F, 0.0F));
    
            return LayerDefinition.create(meshdefinition, 64, 32);
        }
    }
}
