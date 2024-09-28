package com.beatdz.data;

import com.beatdz.server.DataCenter;

public class ListSkill {

    public static Skill skillChinhXac = findSkill(50, 0);
    public static Skill skillNeTranh = findSkill(51, 0);
    public static Skill skillChiMang = findSkill(52, 0);
    
    public static Skill skillKp = findSkill(53, 0);
    public static Skill skillHp = findSkill(54, 0);
    
    public static Skill skillAttack = findSkill(30, 1);
    
    public static Skill skillKame = findSkill(6, 1);
    public static Skill skillFinalFlash = findSkill(0, 1);
    public static Skill skillMasenko = findSkill(12, 1);
    public static Skill skillMakankosappo = findSkill(18, 1);
    
    
    public static Skill skillBienHinhGoku = findSkill(8, 1);
    public static Skill skillBienHinhCadic = findSkill(3, 1);
    public static Skill skillBienHinhGohan = findSkill(15, 1);
    public static Skill skillBienHinhPocolo = findSkill(21, 1);
    //"Gôku", "Cađíc", "Gôhan", "Pôcôlô"};

    public static Skill findSkill(int isSkillTemplate, int level) {
        return Skill.findSkill(isSkillTemplate, level);
    }

    public static Skill getSkillChuong(int typeChar) {
        if (typeChar == 0) {
            return skillKame.a();
        }
        if (typeChar == 1) {
            return skillFinalFlash.a();
        }
        if (typeChar == 2) {
            return skillMasenko.a();
        }
        if (typeChar == 3) {
            return skillMakankosappo.a();
        }
        return null;
    }

    public static Skill getSkillBienHinh(byte typeChar) {
        if (typeChar == 0) {
            return skillBienHinhGoku.a();
        }
        if (typeChar == 1) {
            return skillBienHinhCadic.a();
        }
        if (typeChar == 2) {
            return skillBienHinhGohan.a();
        }
        if (typeChar == 3) {
            return skillBienHinhPocolo.a();
        }
        return null;
    }
}
