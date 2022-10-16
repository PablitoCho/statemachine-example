package com.example.vendingmachine.config;

import com.example.vendingmachine.enums.Events;
import com.example.vendingmachine.enums.States;
import com.example.vendingmachine.service.VendingMachineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.guard.Guard;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;


@Slf4j
@Configuration
@EnableStateMachine
public class StateMachineConfig extends StateMachineConfigurerAdapter<States, Events> {

    @Override
    public void configure(StateMachineStateConfigurer<States, Events> states)
            throws Exception {
            states
                .withStates()
                .initial(States.S0)
                .state(States.S5)
                .state(States.S10)
                .state(States.S15)
                .state(States.S20)
                .state(States.S25)
                .state(States.S30);
//                .states(EnumSet.allOf(States.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<States, Events> transitions)
            throws Exception {
        transitions
            // insert nickel
            .withExternal()
                .source(States.S0).target(States.S5).event(Events.InsertNickel)
                .guard(insertGuard()).action(insertAction())
            .and()
                .withExternal()
                .source(States.S5).target(States.S10).event(Events.InsertNickel)
                .guard(insertGuard()).action(insertAction())
            .and()
                .withExternal()
                .source(States.S10).target(States.S15).event(Events.InsertNickel)
                .guard(insertGuard()).action(insertAction())
            .and()
                .withExternal()
                .source(States.S15).target(States.S20).event(Events.InsertNickel)
                .guard(insertGuard()).action(insertAction())
            .and()
                .withExternal()
                .source(States.S20).target(States.S25).event(Events.InsertNickel)
                .guard(insertGuard()).action(insertAction())
            .and()
                .withExternal()
                .source(States.S25).target(States.S30).event(Events.InsertNickel)
                .guard(insertGuard()).action(insertAction())
            .and()
                .withInternal()
                .source(States.S30).event(Events.InsertNickel)
            // insert dime
            .and()
                .withExternal()
                .source(States.S0).target(States.S10).event(Events.InsertDime)
                .guard(insertGuard()).action(insertAction())
            .and()
                .withExternal()
                .source(States.S5).target(States.S15).event(Events.InsertDime)
                .guard(insertGuard()).action(insertAction())
            .and()
                .withExternal()
                .source(States.S10).target(States.S20).event(Events.InsertDime)
                .guard(insertGuard()).action(insertAction())
            .and()
                .withExternal()
                .source(States.S15).target(States.S25).event(Events.InsertDime)
                .guard(insertGuard()).action(insertAction())
            .and()
                .withExternal()
                .source(States.S20).target(States.S30).event(Events.InsertDime)
                .guard(insertGuard()).action(insertAction())
            .and()
                .withExternal()
                .source(States.S25).target(States.S30).event(Events.InsertDime)
                .guard(insertGuard()).action(insertAction())
            .and()
                .withInternal()
                .source(States.S30).event(Events.InsertDime)
            // insert quarter
            .and()
                .withExternal()
                .source(States.S0).target(States.S25).event(Events.InsertQuarter)
                .guard(insertGuard()).action(insertAction())
            .and()
                .withExternal()
                .source(States.S5).target(States.S30).event(Events.InsertQuarter)
                .guard(insertGuard()).action(insertAction())
            .and()
                .withExternal()
                .source(States.S10).target(States.S30).event(Events.InsertQuarter)
                .guard(insertGuard()).action(insertAction())
            .and()
                .withExternal()
                .source(States.S15).target(States.S30).event(Events.InsertQuarter)
                .guard(insertGuard()).action(insertAction())
            .and()
                .withExternal()
                .source(States.S20).target(States.S30).event(Events.InsertQuarter)
                .guard(insertGuard()).action(insertAction())
            .and()
                .withExternal()
                .source(States.S25).target(States.S30).event(Events.InsertQuarter)
                .guard(insertGuard()).action(insertAction())
            .and()
                .withInternal()
                .source(States.S30).event(Events.InsertQuarter);
    }

    @Override
    public void configure(StateMachineConfigurationConfigurer<States, Events> config) throws Exception {
        StateMachineListenerAdapter<States, Events> adapter = new StateMachineListenerAdapter<>(){
            @Override
            public void stateChanged(State<States, Events> fromState, State<States, Events> toState) {
                log.info("State changed from {} to {}. Current cents {}",
                        fromState == null ? "start": fromState.getId().toString(),
                        toState.getId().toString(),
                        VendingMachineService.getInsertedCents());
            }
        };
        config.withConfiguration().listener(adapter);
    }

    @Bean
    InsertAction insertAction() {
        return new InsertAction();
    }

    @Bean
    InsertGuard insertGuard() {
        return new InsertGuard();
    }

    class InsertAction implements Action<States, Events>  {
        @Override
        public void execute(StateContext<States, Events> stateContext) {
            int currentCents = VendingMachineService.getInsertedCents();
            switch(stateContext.getEvent()) {
                case InsertNickel:
                    VendingMachineService.setInsertedCents(currentCents + 5);
                    break;
                case InsertDime:
                    VendingMachineService.setInsertedCents(currentCents + 10);
                    break;
                case InsertQuarter:
                    VendingMachineService.setInsertedCents(currentCents + 25);
                    break;
            }
        }
    }

    class InsertGuard implements Guard<States, Events> {
        @Override
        public boolean evaluate(StateContext<States, Events> stateContext) {
            int currentCents = VendingMachineService.getInsertedCents();
            switch(stateContext.getEvent()) {
                case InsertNickel:
                    return currentCents + 5 <= 30;
                case InsertDime:
                    return currentCents + 10 <= 30;
                case InsertQuarter:
                    return currentCents + 25 <= 30;
            }
            return false;
        }
    }
}
