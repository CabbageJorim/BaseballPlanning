package com.plan.baseball.model.dto.data

import com.plan.baseball.model.dto.team.Entry
import javax.persistence.*

@Entity
@Table(name="S_B_DETAIL")
data class BatterDetailSeasonRecord (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long? = 0L,

    @ManyToOne(targetEntity = Entry::class, fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name="ENTRY_ID", nullable = false)
    val entry: Entry,

    @Column(name="SINGLE_HIT", nullable = false)
    val singleHit: Int,   //1루타 -> 중요도 떨어짐
    @Column(name="DOUBLE_HIT", nullable = false)
    val doubleHit: Int,   //2루타 -> 중요도 떨어짐
    @Column(name="TRIPLE_HIT", nullable = false)
    val tripleHit: Int,   //3루타 -> 중요도 떨어짐
    @Column(name="BASE", nullable = false)
    val base:Int, //전체루타 -> 중요도 떨어짐
    @Column(name="F_SB", nullable = false)
    val fsb: Int, //도실 -> 중요도 떨어짐
    @Column(name="I_BB", nullable = false)
    val iBB: Int, //고의사구 -> 중요도 떨어짐
    @Column(name="PBP", nullable = false)
    val PBP: Int,        //사구 -> 중요도 떨어짐
    @Column(name="DOUBLE_PLAY", nullable = false)
    val doublePlay:Int  //병살타 -> 중요도 떨어짐
)