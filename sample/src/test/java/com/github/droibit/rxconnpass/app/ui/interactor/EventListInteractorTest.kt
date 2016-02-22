package com.github.droibit.rxconnpass.app.ui.interactor

import com.github.droibit.rxconnpass.Event
import com.github.droibit.rxconnpass.app.model.SearchAction
import com.github.droibit.rxconnpass.app.model.exception.NetworkDisconnectedException
import com.github.droibit.rxconnpass.app.ui.view.EventListView
import com.github.droibit.rxconnpass.app.ui.view.transition.TransitionDetailEvent
import com.github.droibit.rxconnpass.app.util.RxSchedulersOverrideRule
import com.github.droibit.rxconnpass.app.util.uninitialized
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnit
import rx.Observable
import rx.lang.kotlin.toObservable
import rx.lang.kotlin.toSingletonObservable
import rx.subscriptions.CompositeSubscription

class EventListInteractorTest {

    @Rule
    @JvmField
    val mockito = MockitoJUnit.rule()
    @Rule
    @JvmField
    val overrideSchedulers = RxSchedulersOverrideRule()

    @Mock
    lateinit var searchAction: SearchAction
    @Mock
    lateinit var view: EventListView
    @Mock
    lateinit var mockEvents: List<Event>

    lateinit var interactor: EventListInteractor

    @Before
    fun setup() {
        interactor = EventListInteractor(action = searchAction,
                                         compositeSubscription = CompositeSubscription())
        interactor.init(view)
    }

    @After
    fun tearDown() {
        interactor.destroy()
    }

    @Test
    fun searchByKeyword() {
        `when`(searchAction.search(anyString())).thenReturn(mockEvents.toSingletonObservable())

        interactor.searchByKeyword("kotlin")

        verify(view, times(1)).showProgress()
        verify(view, times(1)).hideProgress()
        verify(view, times(1)).showContent(mockEvents)
        verify(view, never()).hideRefreshProgress()
        verify(view, never()).showError(uninitialized())
    }

    @Test
    fun researchByKeyword() {
        `when`(searchAction.search(anyString())).thenReturn(mockEvents.toSingletonObservable())
        `when`(searchAction.research()).thenReturn(mockEvents.toSingletonObservable())

        interactor.searchByKeyword("kotlin")
        interactor.researchByKeyword()

        verify(view, times(1)).showProgress()
        verify(view, times(1)).hideProgress()
        verify(view, times(2)).showContent(mockEvents)
        verify(view, times(1)).hideRefreshProgress()
        verify(view, never()).showError(uninitialized())
    }

    @Test
    fun occurErrorInSearchByKeyword() {
        val t = NetworkDisconnectedException()
        `when`(searchAction.search(anyString())).thenReturn(t.toObservable())

        interactor.searchByKeyword("kotlin")

        verify(view, times(1)).showProgress()
        verify(view, never()).hideProgress()
        verify(view, never()).showContent(uninitialized())
        verify(view, never()).hideRefreshProgress()
        verify(view, times(1)).showError(t)
    }

    @Test
    fun occurErrorInResearchByKeyword() {
        `when`(searchAction.search(anyString())).thenReturn(Observable.just(mockEvents))

        val t = NetworkDisconnectedException()
        `when`(searchAction.research()).thenReturn(t.toObservable())

        interactor.searchByKeyword("kotlin")
        interactor.researchByKeyword()

        verify(view, times(1)).showProgress()
        verify(view, times(1)).hideProgress()
        verify(view, times(1)).showContent(mockEvents)
        verify(view, never()).hideRefreshProgress()
        verify(view, times(1)).showError(t)
    }

    @Test
    fun eventClick() {
        val event = mock(TransitionDetailEvent::class.java)
        interactor.eventClick().call(event)

        verify(view, times(1)).navigateToEventDetail(event)
    }

    @Test
    fun eventOrderMenuClick() {
        interactor.eventOrderMenuClick()

        verify(view, times(1)).showEventOrderDialog()
    }

    @Test
    fun settingsMenuClick() {
        interactor.settingsMenuClick()

        verify(view, times(1)).navigateToSetting()
    }
}