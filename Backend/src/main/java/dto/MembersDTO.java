/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Member;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Henrik
 */
public class MembersDTO {
        List<MemberDTO> all = new ArrayList<>();

    public MembersDTO(List<Member> memberEntities) {
        for (Member memberEntity : memberEntities) {
            all.add(new MemberDTO(memberEntity));
        }
    }

    public List<MemberDTO> getAll() {
        return all;
    }

    public void setAll(List<MemberDTO> all) {
        this.all = all;
    }
}
